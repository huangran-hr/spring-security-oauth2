package com.example.oauth2.sso.server.contoller;

import com.example.oauth2.sso.server.domain.TbUser;
import com.example.oauth2.sso.server.service.TbPermissionService;
import com.example.oauth2.sso.server.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: HR
 * @Date: 2020/5/9 17:22
 * @Description:
 */
@Controller
public class LoginController {

    @Autowired
    private TbUserService tbUserService;

    @Autowired
    private TbPermissionService tbPermissionService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PreAuthorize("hasAuthority('SystemContext')")
    @GetMapping("/user")
    @ResponseBody
    public TbUser user() {
        return tbUserService.getByUsername("admin");
    }
}
