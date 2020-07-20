package com.itheima.web.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
/**
 * @Classname LoginController
 * @Description 用户登录模块
 * @Date 2019-3-14 14:15
 * @Created by CrazyStone
 */
@Controller
public class LoginController {
    // 向登录页面跳转，同时封装原始页面地址
    @GetMapping(value = "/login")
    public String login(HttpServletRequest request, Map map) {
        // 分别获取请求头和参数url中的原始访问路径
        String referer = request.getHeader("Referer");
        String url = request.getParameter("url");
        System.out.println("referer= "+referer);
        System.out.println("url= "+url);

        // 如果参数url中已经封装了原始页面路径，直接返回该路径
        if (url!=null && !url.equals("")){
            map.put("url",url);
            // 如果请求头本身包含登录，将重定向url设为空，让后台通过用户角色进行选择跳转
        }else if (referer!=null && referer.contains("/login")){
            map.put("url", "");
        }else {
            // 否则的话，就记住请求头中的原始访问路径
            map.put("url", referer);
        }
        return "comm/login";
    }

    // 对Security拦截的无权限访问异常处理路径映射
    @GetMapping(value = "/errorPage/{page}/{code}")
    public String AccessExecptionHandler(@PathVariable("page") String page, @PathVariable("code") String code) {
        return page+"/"+code;
    }
}

