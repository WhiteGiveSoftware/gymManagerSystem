package com.gdou.gas.controller.common;

import com.gdou.gas.util.CpachaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * 系统验证码公用控制器
 */
@Controller
@RequestMapping("/cpacha")
public class CpachaController {
//通用验证码生成器
    @RequestMapping(value = "/generate_cpacha",method = RequestMethod.GET)
    public void generateCpacha(
            @RequestParam(name="vl",defaultValue = "4") Integer vl,
            @RequestParam(name="fs",defaultValue = "21") Integer fs,
            @RequestParam(name="w",defaultValue = "98") Integer w,
            @RequestParam(name="h",defaultValue = "33") Integer h,
            @RequestParam(name="method",defaultValue = "admin_login") String method,//验证类型，传入session
            HttpServletRequest request,
            HttpServletResponse response){
        CpachaUtil cpachaUtil = new CpachaUtil(vl,fs,w,h);
        String generatorVCode = cpachaUtil.generatorVCode();
        //将生产的验证码放进session，以供后面程序使用
        request.getSession().setAttribute(method,generatorVCode);
        try {
            ImageIO.write(cpachaUtil.generatorVCodeImage(generatorVCode,true),"gif",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
