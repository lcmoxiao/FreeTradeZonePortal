package com.ftzp.controller.lc.user;

import com.ftzp.cache.RedisObjCache;
import com.ftzp.controller.lc.LoginController;
import com.ftzp.pojo.lc.user.User;
import com.ftzp.service.lc.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.ftzp.controller.lc.LoginController.getRemoteIP;

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource(name = "redisObjCache")
    RedisObjCache redisObjCache;

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    List<User> getUser() {
        return userService.getUser(null);
    }

    @RequestMapping(value = "/{uId}", method = RequestMethod.DELETE)
    @ResponseBody
    String deleteUser(@PathVariable Integer uId, HttpServletRequest re) {
        User u = (User) redisObjCache.getValue(getRemoteIP(re) + "u");
        logger.info(u.getuName() + "删除了用户uId：" + uId);

        userService.deleteUser(uId);
        return "redirect:/userManagement";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    String insertUser(@RequestParam("uName") String uName, HttpServletRequest re, @RequestParam("uPass") String uPass, @RequestParam("rId") Integer rId) {
        User u = (User) redisObjCache.getValue(getRemoteIP(re) + "u");
        logger.info(u.getuName() + "删除了用户uName：" + uName);
        User user = new User();
        user.setrId(rId);
        user.setuName(uName);
        user.setuPass(uPass);
        userService.insertUser(user);
        return "redirect:/userManagement";
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    String updateUser(@RequestParam("uId") Integer uId, HttpServletRequest re, @RequestParam("uName") String uName, @RequestParam("uPass") String uPass, @RequestParam("rId") Integer rId) {
        User u = (User) redisObjCache.getValue(getRemoteIP(re) + "u");
        logger.info(u.getuName() + "删除了用户uId：" + uId);
        User user = new User();
        user.setuId(uId);
        user.setrId(rId);
        user.setuName(uName);
        user.setuPass(uPass);
        userService.updateUser(user);
        return "redirect:/userManagement";
    }

}
