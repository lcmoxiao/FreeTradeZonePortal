package com.ftzp.controller.lc;


import com.ftzp.pojo.lc.User;
import com.ftzp.service.lc.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    String login(User user, HttpSession session) {
        User u = userService.checkUser(user);
        if (u != null) {
            session.setAttribute("user", u);
            return "loginSuccess";
        }
        return "loginFailed";
    }


}
