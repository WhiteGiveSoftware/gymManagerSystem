package com.gdou.gas.controller.home;

import com.gdou.gas.entity.admin.OperaterLog;
import com.gdou.gas.entity.admin.User;
import com.gdou.gas.entity.home.Account;
import com.gdou.gas.service.admin.OperaterLogService;
import com.gdou.gas.service.home.AccoutService;
import com.gdou.gas.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

//前台控制器
@RequestMapping("/home")
@Controller
@Configurable
public class HomeController {
    @Autowired
    private AccoutService accoutService;

    @Autowired
    private OperaterLogService operaterLogService;



    /**
     * 前台页面
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(String name, Model model) {
        return "admin/home/homeindex";
    }

    /**
     * 前台登陆页面
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(String name, Model model) {
        return "admin/home/homelogin";
    }

    /**
     * 前台注册页面
     */
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String register(String name, Model model) {
        return "admin/home/homeregister";
    }

    /**
     * 客户注册提交表单处理方法
     */
    @RequestMapping(value="/register",method=RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> login(HttpServletRequest request, Account account){
        Account a =  accoutService.findByUsername(account.getUsername());
        if (a != null){
            return Result.error(CodeMsg.CPACHA_EMPTY);
        }
        account.setCode(UuidUtil.getUuid());
        account.setStatus("N");
        accoutService.save(account);

        String content=
                "<html><head></head><body><h1>请点击以下链接激活注册账号</h1><h3><a href='http://localhost:8085/home/active?code="
                        + account.getCode() + "'>http://localhost:8085/home/active?code=" + account.getCode()
                        + "</href></h3></body></html>";

//                "<a href='http://localhost:8085/home/active?code="+account.getCode()+"'>点击激活体育馆账号</a>";

        MailUtils.sendMail(account.getEmail(),content,"激活邮件");
        //将登陆记录写入日志库
        OperaterLog operaterLog = new OperaterLog();
        operaterLog.setOperator(account.getUsername());
        operaterLog.setContent("前台客户【"+account.getUsername()+"】于【" + StringUtil.getFormatterDate(new Date(), "yyyy-MM-dd HH:mm:ss") + "】注册！");
        operaterLogService.save(operaterLog);

        return Result.success(true);
    }
    /**
     * 激活用户
     * @param code
     * @return
     */
    @RequestMapping(value = "/active",method = RequestMethod.GET)
    @ResponseBody
    public String active(String code) {
        //1.根据激活码查询用户对象
        Account account = accoutService.findByCode(code);
        if(account != null){
            //2.修改激活状态
            account.setStatus("Y");
            account.setId(account.getId());
            accoutService.save(account);
        return "pass";
        }else {
            return "error";
        }

    }


    /**
     * 用户登录提交表单处理方法
     */
    @RequestMapping(value="/login",method=RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> login(HttpServletRequest request, Account account, String cpacha){
        if(account == null){
            return Result.error(CodeMsg.DATA_ERROR);
        }
//        //用统一验证实体方法验证是否合法
//        CodeMsg validate = ValidateEntityUtil.validate(user);
//        if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
//            return Result.error(validate);
//        }
        //表示实体信息合法，开始验证验证码是否为空
        if(StringUtils.isEmpty(cpacha)){
            return Result.error(CodeMsg.CPACHA_EMPTY);
        }
        //说明验证码不为空，从session里获取验证码
        Object attribute = request.getSession().getAttribute("admin_login");
        if(attribute == null){
            return Result.error(CodeMsg.SESSION_EXPIRED);
        }
        //表示session未失效，进一步判断用户填写的验证码是否正确
        if(!cpacha.equalsIgnoreCase(attribute.toString())){
            return Result.error(CodeMsg.CPACHA_ERROR);
        }
        //表示验证码正确，开始查询数据库，检验密码是否正确
        Account findByUsername = accoutService.findByUsername(account.getUsername());
        if(findByUsername == null){
            return Result.error(CodeMsg.ADMIN_USERNAME_NO_EXIST);
        }
        //表示用户存在，进一步对比密码是否正确
        if(!findByUsername.getPassword().equals(account.getPassword())){
            return Result.error(CodeMsg.ADMIN_PASSWORD_ERROR);
        }
        //表示用户存在，进一步验证客户的状态
        if(!findByUsername.getStatus().equals("Y")){
            return Result.error(CodeMsg.ADMIN_Status_ERROR);
        }

        //检查一切符合，可以登录，将用户信息存放至session
        request.getSession().setAttribute("home_user", findByUsername);
        //销毁session中的验证码
        request.getSession().setAttribute("admin_login", null);
        //将登陆记录写入日志库
        OperaterLog operaterLog = new OperaterLog();
        operaterLog.setOperator(account.getUsername());
        operaterLog.setContent("前台客户【"+account.getUsername()+"】于【" + StringUtil.getFormatterDate(new Date(), "yyyy-MM-dd HH:mm:ss") + "】登录系统！");
        operaterLogService.save(operaterLog);
        return Result.success(true);
    }
    /**
     * 注销登录
     * @return
     */
    @RequestMapping(value="/logout")
    public String logout(HttpServletRequest request){
        request.getSession().setAttribute("home_user", null);
        return "redirect:login";
    }






}
