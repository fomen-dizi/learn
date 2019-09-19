package com.jys.weibo.controller.test;

import com.jys.weibo.model.test.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainControler {
    @RequestMapping("/index")
    public String showindex(){
        return "test/index";
        }
    @RequestMapping("/login1")
    public String showlogin(){
         return "test/login";
         }    
    @RequestMapping("/yanzheng")
    //也可以添加标注@ResponseBody 返回数据给页面（js跳转）
    public String yanzheng(User user , HttpServletRequest request){
        //只是密码的简单判断，哈哈。当然也可以连数据判断
        if("1234".equals(user.getPassword())){
            request.getSession().setAttribute("users", user.getName());
            //先添加到session,在跳转
            return "test/index";
        }else {
            return "test/login";
        }
    }
}
