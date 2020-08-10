package com.ftzp.controller.lc;

import com.ftzp.pojo.lc.User;
import com.ftzp.service.lc.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    List<User> getUser() {
        return userService.getUser(null);
    }

    @RequestMapping(value = "/delete/{uId}", method = RequestMethod.GET)
    String deleteUser(@PathVariable Integer uId) {
        userService.deleteUser(uId);
        return "redirect:/userManagement";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    String insertUser(@RequestParam("uName") String uName, @RequestParam("uPass") String uPass, @RequestParam("rId") Integer rId) {
        User user = new User();
        user.setrId(rId);
        user.setuName(uName);
        user.setuPass(uPass);
        userService.insertUser(user);
        return "redirect:/userManagement";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    String updateUser(@RequestParam("uId") Integer uId, @RequestParam("uName") String uName, @RequestParam("uPass") String uPass, @RequestParam("rId") Integer rId) {
        User user = new User();
        user.setuId(uId);
        user.setrId(rId);
        user.setuName(uName);
        user.setuPass(uPass);
        userService.insertUser(user);
        return "redirect:/userManagement";
    }

}
