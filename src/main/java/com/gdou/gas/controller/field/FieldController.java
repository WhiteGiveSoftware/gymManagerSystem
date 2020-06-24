package com.gdou.gas.controller.field;

import com.gdou.gas.entity.admin.OperaterLog;
import com.gdou.gas.entity.admin.User;
import com.gdou.gas.entity.field.Field;
import com.gdou.gas.service.admin.OperaterLogService;
import com.gdou.gas.service.field.FieldService;
import com.gdou.gas.util.*;
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
        model.addAttribute("field", fieldService.find(id));

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
        request.getSession().setAttribute("field", field);
        Field f = (Field)request.getSession().getAttribute("field");
        User u = (User)request.getSession().getAttribute("user");
        OperaterLog operaterLog = new OperaterLog();
        operaterLog.setOperator(u.getUsername());
        operaterLog.setContent("用户【"+u.getUsername()+"】于【"+ StringUtil.getFormatterDate(new Date(), "yyyy-MM-dd HH:mm:ss") + "】添加了场地"+f.getFieldName());
        operaterLogSer.save(operaterLog);
        return Result.success(true);
    }

    /**
     *
     * 修改场地信息
     */

    @RequestMapping(value="/edit", method = RequestMethod.POST)
    @ResponseBody
    //field是前端传入的实体类
    //existField是选中已存在的要修改的场地，浏览器路径正确显示选中的场地ID，前端无值显示
    public Result<Boolean> edit(HttpServletRequest request, Field field){
//        request.getSession().setAttribute("fieldlist", fieldService.findAll());
        if(field == null){
            Result.error(CodeMsg.DATA_ERROR);
        }
        if(field.getId() == null){
            Result.error(CodeMsg.ADMIN_FIELD_ID_EMPTY);
        }
        Field existField = fieldService.find(field.getId());
        if(existField == null){
            Result.error(CodeMsg.ADMIN_FIELD_ID_ERROR);
        }

        // 此处开始把内容写入数据库，existField为空
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

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> delete(HttpServletRequest request,
                                   @RequestParam(name = "id", required = true) Long id){
        try{
            fieldService.delete(id);
        }catch (Exception e){
            return Result.error(CodeMsg.ADMIN_FIELD_DELETE_ERROR);
        }
        return Result.success(true);
    }
}
