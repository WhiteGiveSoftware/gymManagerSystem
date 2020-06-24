package com.gdou.gas.controller.equipment;



import com.gdou.gas.entity.admin.OperaterLog;

import com.gdou.gas.entity.admin.User;
import com.gdou.gas.entity.equipment.Rent;
import com.gdou.gas.service.admin.OperaterLogService;
import com.gdou.gas.service.equipment.RentService;
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

//控制类
@RequestMapping("/equipment")
@Controller
public class RentController {

    @Autowired
    private RentService rentService;

    @Autowired
    private OperaterLogService operaterLogService;


    /**
     * 列表页面
     */
    @RequestMapping(value = "/rent_list", method = RequestMethod.GET)
    public String index(Model model, Rent rent, PageBean<Rent> pageBean) {
        //model.addAttribute("userList", userService.findAll());
        //user.setUsername(username);
        model.addAttribute("pageBean", rentService.findByname(rent,pageBean));
        return "admin/equipment/rent_list";
    }


    /**
     * 添加页面
     */
    @RequestMapping(value = "/rent_add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("rentList", rentService.findAll());
        return "admin/equipment/rent_add";
    }

    /**
     * 编辑页面
     */
    @RequestMapping(value = "/rent_edit", method = RequestMethod.GET)
    public String edit(Model model,
                       @RequestParam(name = "id", required = true) Long id) {
        model.addAttribute("rentList", rentService.findAll());
        model.addAttribute("rent", rentService.find(id));

        return "admin/equipment/rent_edit";
    }


    /**
     * 添加表单数据提交
     */
    @RequestMapping(value = "/rent_add", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> add(HttpServletRequest request, Rent rent) {
        if (rent == null) {
            Result.error(CodeMsg.DATA_ERROR);
        }
        //用统一验证实体方法验证是否合法
        CodeMsg validate = ValidateEntityUtil.validate(rent);
        if (validate.getCode() != CodeMsg.SUCCESS.getCode()) {
            return Result.error(validate);
        }
        //表示验证都通过，开始添加数据库
        if (rentService.save(rent) == null) {
            Result.error(CodeMsg.ADMIN_USE_ADD_ERROR);
        }
        //数据库添加操作成功,记录日志
        User u = (User) request.getSession().getAttribute("user");
        OperaterLog operaterLog = new OperaterLog();
        operaterLog.setOperator(u.getUsername());
        operaterLog.setContent("用户【" + u.getUsername() + "】于【" + StringUtil.getFormatterDate(new Date(), "yyyy-MM-dd HH:mm:ss") + "】添加了新器材租用单");
        operaterLogService.save(operaterLog);


        return Result.success(true);
    }


    /**
     * 编辑表单数据提交
     */
    @RequestMapping(value = "/rent_edit", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> edit(HttpServletRequest request, Rent rent) {
        if (rent == null) {
            Result.error(CodeMsg.DATA_ERROR);
        }
        if (rent.getId() == null) {
            Result.error(CodeMsg.ADMIN_USER_ID_EMPTY);
        }
        //用统一验证实体方法验证是否合法
        CodeMsg validate = ValidateEntityUtil.validate(rent);
        if (validate.getCode() != CodeMsg.SUCCESS.getCode()) {
            return Result.error(validate);
        }
        Rent existRent= rentService.find(rent.getId());
        if (existRent == null) {
            Result.error(CodeMsg.ADMIN_USER_ID_ERROR);
        }

        //表示验证都通过，开始添加数据库
        existRent.setName(rent.getName());
        existRent.setTel(rent.getTel());
        existRent.setEquipmentname(rent.getEquipmentname());
        existRent.setDescription(rent.getDescription());
        existRent.setUpdateTime(new Date());
        if (rentService.save(existRent) == null) {
            Result.error(CodeMsg.ADMIN_USE_ADD_ERROR);
        }

//        //数据库添加操作成功,记录日志
//        User u = (User) request.getSession().getAttribute("user");
//        OperaterLog operaterLog = new OperaterLog();
//        operaterLog.setOperator(u.getUsername());
//        operaterLog.setContent("用户【"+u.getUsername()+"】于【" + StringUtil.getFormatterDate(new Date(), "yyyy-MM-dd HH:mm:ss") + "】添加了一位新用户");
//        operaterLogService.save(operaterLog);
        return Result.success(true);
    }

    /**
     * 删除用户记录
     */
    @RequestMapping(value = "/rent_delete", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> delete(HttpServletRequest request, @RequestParam(name = "id", required = true) Long id) {
        try {
            rentService.delete(id);
        } catch (Exception e) {
            return Result.error(CodeMsg.ADMIN_USE_DELETE_ERROR);
        }

//        //数据库添加操作成功,记录日志
//        User u = (User) request.getSession().getAttribute("user");
//        OperaterLog operaterLog = new OperaterLog();
//        operaterLog.setOperator(u.getUsername());
//        operaterLog.setContent("用户【"+u.getUsername()+"】于【" + StringUtil.getFormatterDate(new Date(), "yyyy-MM-dd HH:mm:ss") + "】添加了一位新用户");
//        operaterLogService.save(operaterLog);
        return Result.success(true);
    }

}
