package com.gdou.gas.controller.admin;

import com.gdou.gas.entity.admin.OperaterLog;
import com.gdou.gas.entity.admin.User;
import com.gdou.gas.service.admin.OperaterLogService;
import com.gdou.gas.service.admin.UserService;
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
@RequestMapping("/user")
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private OperaterLogService operaterLogService;

    /**
     * 列表页面
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String index(Model model, User user, PageBean<User> pageBean) {
        //model.addAttribute("userList", userService.findAll());
        //user.setUsername(username);
        model.addAttribute("pageBean", userService.findByname(user,pageBean));
        return "admin/user/list";
    }


    /**
     * 添加页面
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("userList", userService.findAll());
        return "admin/user/add";
    }

    /**
     * 编辑页面
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Model model,
                       @RequestParam(name = "id", required = true) Long id) {
        model.addAttribute("userList", userService.findAll());
        model.addAttribute("user", userService.find(id));

        return "admin/user/edit";
    }


    /**
     * 添加表单数据提交
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> add(HttpServletRequest request, User user) {
        if (user == null) {
            Result.error(CodeMsg.DATA_ERROR);
        }
        //用统一验证实体方法验证是否合法
        CodeMsg validate = ValidateEntityUtil.validate(user);
        if (validate.getCode() != CodeMsg.SUCCESS.getCode()) {
            return Result.error(validate);
        }
        //表示验证都通过，开始添加数据库
        if (userService.save(user) == null) {
            Result.error(CodeMsg.ADMIN_USE_ADD_ERROR);
        }
        //数据库添加操作成功,记录日志
        User u = (User) request.getSession().getAttribute("user");
        OperaterLog operaterLog = new OperaterLog();
        operaterLog.setOperator(u.getUsername());
        operaterLog.setContent("用户【" + u.getUsername() + "】于【" + StringUtil.getFormatterDate(new Date(), "yyyy-MM-dd HH:mm:ss") + "】添加了一位新用户");
        operaterLogService.save(operaterLog);


        return Result.success(true);
    }


    /**
     * 编辑表单数据提交
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> edit(HttpServletRequest request, User user) {
        if (user == null) {
            Result.error(CodeMsg.DATA_ERROR);
        }
        if (user.getId() == null) {
            Result.error(CodeMsg.ADMIN_USER_ID_EMPTY);
        }
        //用统一验证实体方法验证是否合法
        CodeMsg validate = ValidateEntityUtil.validate(user);
        if (validate.getCode() != CodeMsg.SUCCESS.getCode()) {
            return Result.error(validate);
        }
        User existUser = userService.find(user.getId());
        if (existUser == null) {
            Result.error(CodeMsg.ADMIN_USER_ID_ERROR);
        }

        //表示验证都通过，开始添加数据库
        existUser.setPassword(user.getPassword());
        existUser.setUsername(user.getUsername());
        existUser.setUpdateTime(new Date());
        if (userService.save(existUser) == null) {
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
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> delete(HttpServletRequest request, @RequestParam(name = "id", required = true) Long id) {
        try {
            userService.delete(id);
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
