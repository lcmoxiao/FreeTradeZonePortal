package com.ftzp.controller.lc;

import com.ftzp.cache.RedisObjCache;
import com.ftzp.pojo.lc.LoginStatistic;
import com.ftzp.pojo.lc.user.Permission;
import com.ftzp.pojo.lc.user.Role;
import com.ftzp.pojo.lc.user.User;
import com.ftzp.service.lc.LoginStatisticService;
import com.ftzp.service.lc.user.PermissionService;
import com.ftzp.service.lc.user.RoleService;
import com.ftzp.service.lc.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import static com.ftzp.TimeUtils.nowTime;

@Controller
public class LoginController {

    @Resource(name = "redisObjCache")
    RedisObjCache redisObjCache;
    @Resource(name = "userService")
    UserService userService;
    @Resource(name = "permissionService")
    PermissionService permissionService;
    @Resource(name = "roleService")
    RoleService roleService;
    @Resource(name = "loginStatisticService")
    LoginStatisticService loginStatisticService;

    public static String getRemoteIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/index")
    String index(HttpServletRequest re) {
        String IP = getRemoteIP(re);
        if (redisObjCache.getValue(IP + "u") != null) return "redirect:/myWork";
        else return "/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    String login(User user, HttpServletRequest re) {
        User u = userService.checkUser(user);
        Role r = roleService.getRole(u.getrId()).get(0);
        ArrayList<Permission> permission = permissionService.getPermission(r.getrPermission());
        if (u != null) {
//            String sId = session.getId();
//            redisObjCache.setValue(sId + "u", u);
//            Role r = roleService.getRole(u.getrId()).get(0);
//            String rP = r.getrPermission();
//            ArrayList<Permission> permission = permissionService.getPermission(rP);
//            redisObjCache.setValue(sId + "p", permission);
            String IP = getRemoteIP(re);
            redisObjCache.setValue(IP + "u", u);
            redisObjCache.setValue(IP + "p", permission);
            LoginStatistic loginStatistic = new LoginStatistic();
            loginStatistic.setLoginIp(IP);
            loginStatistic.setLoginTime(nowTime());
            loginStatistic.setuId(u.getuId());
            loginStatisticService.insertStatistic(loginStatistic);
            return "loginSuccess";
        }
        return "loginFailed";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    String logout(HttpServletRequest re) {
        String IP = getRemoteIP(re);
        redisObjCache.delValue(IP + "u");
        redisObjCache.delValue(IP + "p");
        System.out.println(redisObjCache.getValue(IP + "u"));
        return "redirect:/index";
    }

}
