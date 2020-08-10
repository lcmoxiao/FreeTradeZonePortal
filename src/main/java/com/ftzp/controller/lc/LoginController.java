package com.ftzp.controller.lc;

import com.ftzp.cache.RedisObjCache;
import com.ftzp.pojo.lc.Permission;
import com.ftzp.pojo.lc.Role;
import com.ftzp.pojo.lc.User;
import com.ftzp.service.lc.PermissionService;
import com.ftzp.service.lc.RoleService;
import com.ftzp.service.lc.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;

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

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    String login(User user, HttpSession session) {
        User u = userService.checkUser(user);
        if (u != null) {
            String sId = session.getId();
            redisObjCache.setValue(sId + "u", u);
            Role r = roleService.getRole(u.getrId()).get(0);
            String rP = r.getrPermission();
            ArrayList<Permission> permission = permissionService.getPermission(rP);
            redisObjCache.setValue(sId + "p", Collections.singletonList(permission));
            return "loginSuccess";
        }
        return "loginFailed";
    }


}
