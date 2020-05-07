package com.hr.spring.security.oauth2.server.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 自定义登陆界面Controller
 */
@Controller
public class BaseMainController {

    @GetMapping("/auth/login")
    public String loginPage(Model model){
        model.addAttribute("loginProcessUrl","/authentication/form");
        return "base-login";
    }

    @GetMapping("/auth/index")
    public String indexPage(Model model){
        String code = (String) model.getAttribute("code");
        return "index";
    }
}
