package com.ftzp.controller.lc;


import com.ftzp.pojo.lc.User;
import com.ftzp.service.lc.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    String defualt() {
        return "/index";
    }


    @RequestMapping("/index")
    String index() {
        return "/index";
    }

    @RequestMapping("/home")
    String home() {
        return "/home";
    }

    @RequestMapping("/login")
    String login(User user, HttpSession session) {
        User u = userService.checkUser(user);
        if (u != null) {
            session.setAttribute("user", u);
            System.out.println("login success");
            return "redirect:/home";
        } else {
            System.out.println("login failed");
        }
        return "/index";
    }


}
