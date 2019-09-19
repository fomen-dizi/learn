package com.jys.weibo.controller;

import com.jys.weibo.model.Weibo;
import com.jys.weibo.service.WbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 描述：test控制器
 */
@Controller
public class TestController {
    @Autowired
    public WbService wbItemService;
    /**
     * 获取表单
     * @param model
     * @return
     */
    @GetMapping("/test/testSubmit")
    public String getForm(Model model){
        Weibo weibo = new Weibo();
        weibo.setId(2);
        model.addAttribute("weibo",weibo);
        return "test/test";
    }

    /**
     * 描述：测试提交
     * @param weibo
     * @return
     */
    @PostMapping("/test/testSubmit")
    public String submit(@ModelAttribute Weibo weibo){
        return "test/testResult";
    }

    @RequestMapping("/test")
    public String signup(Model model){
        return "test/test1";
    }
}
