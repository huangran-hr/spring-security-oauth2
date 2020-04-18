package com.hr.springsecurityoauth2.resource.controller;

import com.hr.springsecurityoauth2.resource.domain.TbPermission;
import com.hr.springsecurityoauth2.resource.service.TbPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PermissionController {

    @Autowired
    private TbPermissionService tbPermissionService;

    @RequestMapping(value = "/permission/",method = RequestMethod.GET)
    @ResponseBody
    public List<TbPermission> product(){
        return tbPermissionService.selectAll();
    }
}
