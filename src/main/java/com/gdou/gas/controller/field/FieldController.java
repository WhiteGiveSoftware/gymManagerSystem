package com.gdou.gas.controller.field;

import com.gdou.gas.annotion.ValidateEntity;
import com.gdou.gas.entity.admin.OperaterLog;
import com.gdou.gas.entity.admin.User;
import com.gdou.gas.entity.field.Field;
import com.gdou.gas.service.admin.OperaterLogService;
import com.gdou.gas.service.admin.UserService;
import com.gdou.gas.service.field.FieldService;
import com.gdou.gas.util.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/field")
public class FieldController {

    @Autowired
    private FieldService fieldService;
    @Autowired
    private OperaterLogService operaterLogSer;



    /**
     * 显示场地列表
     * @param model
     * @param field
     * @param pageBean
     * @return "admin/field/list"
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String fieldList(Model model, Field field, PageBean<Field> pageBean){
        model.addAttribute("pageBean", fieldService.findByField(field, pageBean));
        return "admin/field/list";
    }

    /**
     * 添加一个场地
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("fieldList", fieldService.findAll());
        return "admin/field/add";
    }


    /**
     * 编辑页面
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model,
                       @RequestParam(name = "id",  required = true) Long id){
        model.addAttribute("fieldList", fieldService.findAll());
        model.addAttribute("field", fieldService.findByFieldId(id));

        return  "admin/field/edit";
    }

    /**
     * 表单相关操作
     */


    /**
     * 添加
     * @param request
     * @param field
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/add", method = RequestMethod.POST)
    public Result<Boolean> add(HttpServletRequest request, Field field){

        if(field == null){
            Result.error(CodeMsg.DATA_ERROR); //提示错误码
        }

        //验证输入的数据是否合法
        CodeMsg validate = ValidateEntityUtil.validate(field);
        if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
            return Result.error(validate);
        }

        //使用save方法添加或者编辑数据库内信息
        if (fieldService.save(field) == null){
            Result.error(CodeMsg.ADMIN_FIELD_ADD_ERROR);
        }

        Field f = (Field)request.getSession().getAttribute("field");
        User u = (User)request.getSession().getAttribute("user");
        OperaterLog operaterLog = new OperaterLog();
        operaterLog.setOperator(u.getUsername());
        operaterLog.setContent("用户【"+u.getUsername()+"】于【"+ StringUtil.getFormatterDate(new Date(), "yyyy-MM-dd HH:mm:ss") + "】添加了场地"+f.getFieldName());
        operaterLogSer.save(operaterLog);

        return Result.success(true);
    }

    /**
     * post表单数据
     */

    @RequestMapping(value="/edit", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> edit(HttpServletRequest request, Field field){
        if(field == null){
            Result.error(CodeMsg.DATA_ERROR);
        }
         // 注意此处
        if(field.getFieldId() == null){
            Result.error(CodeMsg.ADMIN_FIELD_ID_EMPTY);
        }

        CodeMsg validate = ValidateEntityUtil.validate(field);
        if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
            return Result.error(validate);
        }

        Field existField = fieldService.findByFieldId(field.getFieldId());
        if(existField == null){
            Result.error(CodeMsg.ADMIN_FIELD_ID_ERROR);
        }

        existField.setFieldName(field.getFieldName());
        existField.setFieldType(field.getFieldType());
        if(fieldService.save(existField) == null){
            Result.error(CodeMsg.ADMIN_FIELD_ADD_ERROR);
        }

        return Result.success(true);
    }

    /**
     * 删除场地
     */
    public  Result<Boolean> delete(HttpServletRequest request,
                                   @RequestParam(name = "id", required = true) Long id){
        try{
            fieldService.delete(id);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(CodeMsg.ADMIN_FIELD_DELETE_ERROR);
        }
        return Result.success(true);
    }
}
