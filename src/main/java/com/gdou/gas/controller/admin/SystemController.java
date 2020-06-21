package com.gdou.gas.controller.admin;


import com.gdou.gas.entity.admin.OperaterLog;
import com.gdou.gas.entity.admin.User;
import com.gdou.gas.service.admin.OperaterLogService;
import com.gdou.gas.service.admin.UserService;
import com.gdou.gas.util.CodeMsg;
import com.gdou.gas.util.Result;
import com.gdou.gas.util.StringUtil;
import com.gdou.gas.util.ValidateEntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import java.util.Date;


//系统控制器
@RequestMapping("/system")
@Controller
@Configurable
public class SystemController {

    @Autowired
    private OperaterLogService operaterLogService;

    @Autowired
    private UserService userService;

    @Value("${spring.datasource.password}")
    private String url;

    /**
     * 登陆页面
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String index(String name, Model model) {
        return "admin/system/login";
    }

    /**
     * 用户登录提交表单处理方法
     */
    @RequestMapping(value="/login",method=RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> login(HttpServletRequest request, User user, String cpacha){
        if(user == null){
            return Result.error(CodeMsg.DATA_ERROR);
        }
        //用统一验证实体方法验证是否合法
        CodeMsg validate = ValidateEntityUtil.validate(user);
        if(validate.getCode() != CodeMsg.SUCCESS.getCode()){
            return Result.error(validate);
        }
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
        User findByUsername = userService.findByUsername(user.getUsername());
        if(findByUsername == null){
            return Result.error(CodeMsg.ADMIN_USERNAME_NO_EXIST);
        }
        //表示用户存在，进一步对比密码是否正确
        if(!findByUsername.getPassword().equals(user.getPassword())){
            return Result.error(CodeMsg.ADMIN_PASSWORD_ERROR);
        }
        //检查一切符合，可以登录，将用户信息存放至session
        request.getSession().setAttribute("user", findByUsername);
        //销毁session中的验证码
        request.getSession().setAttribute("admin_login", null);
        //将登陆记录写入日志库
        OperaterLog operaterLog = new OperaterLog();
        operaterLog.setOperator(user.getUsername());
        operaterLog.setContent("用户【"+user.getUsername()+"】于【" + StringUtil.getFormatterDate(new Date(), "yyyy-MM-dd HH:mm:ss") + "】登录系统！");
        operaterLogService.save(operaterLog);
        return Result.success(true);
    }

    /**
     * 登录成功后的系统主页
     */
    @RequestMapping(value="/index")
    public String index(Model model){
        model.addAttribute("operatorLogs", operaterLogService.findLastestLog(10));

        return "admin/system/sysindex";
    }
    //测试方法
    @RequestMapping(value = "/test")
    @ResponseBody
    public boolean index(Long id) {
        OperaterLog operaterLog = new OperaterLog();
        operaterLog.setOperator("admin123");
        operaterLog.setContent("视察民情");
        operaterLog.setId(id);
        operaterLog.setCreaTime(operaterLogService.findById(id).getCreaTime());
        operaterLogService.save(operaterLog);
        return true;

    }
}
