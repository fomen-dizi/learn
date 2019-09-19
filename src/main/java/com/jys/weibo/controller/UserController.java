package com.jys.weibo.controller;

import com.jys.weibo.model.WbUser;
import com.jys.weibo.service.WbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 描述：用户控制器
 */
@Controller
public class UserController {
    @Autowired
    public WbUserService wbUserService;

    @RequestMapping("/loginform")
    public String login(Model model){
        model.addAttribute("user",new WbUser());
        return "login";
    }




}
