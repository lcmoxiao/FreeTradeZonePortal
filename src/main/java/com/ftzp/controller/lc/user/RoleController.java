package com.ftzp.controller.lc.user;

import com.ftzp.cache.RedisObjCache;
import com.ftzp.pojo.lc.user.Role;
import com.ftzp.pojo.lc.user.User;
import com.ftzp.service.lc.user.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.ftzp.PermissionParseUtil.generateHexStrByArray;
import static com.ftzp.controller.lc.LoginController.getRemoteIP;

@Controller
@RequestMapping("/role")
public class RoleController {

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Resource(name = "redisObjCache")
    RedisObjCache redisObjCache;
    @Resource(name = "roleService")
    RoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    List<Role> getRole() {
        return roleService.getRole(null);
    }

    @RequestMapping(value = "/{rId}", method = RequestMethod.DELETE)
    @ResponseBody
    String deleteRole(@PathVariable Integer rId, HttpServletRequest re) {
        User u = (User) redisObjCache.getValue(getRemoteIP(re) + "u");
        logger.info(u.getuName() + "删除了角色rId：" + rId);
        roleService.deleteRole(rId);
        return "redirect:/roleManagement";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    String insertRole(@RequestParam("rName") String rName, HttpServletRequest re,
                      @RequestParam("permissions") String[] permissions) {
        User u = (User) redisObjCache.getValue(getRemoteIP(re) + "u");
        logger.info(u.getuName() + "删除了角色rName：" + rName);
        Role role = new Role();
        role.setrName(rName);
        String rpermission = generateHexStrByArray(permissions);
        System.out.println(rpermission);
        role.setrPermission(rpermission);
        roleService.insertRole(role);
        return "redirect:/roleManagement";
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    String updateRole(@RequestParam("rId") Integer rId, HttpServletRequest re, @RequestParam("rName") String rName, @RequestParam("permissions") String[] permissions) {
        User u = (User) redisObjCache.getValue(getRemoteIP(re) + "u");
        logger.info(u.getuName() + "更新了角色rId：" + rId);
        Role role = new Role();
        role.setrId(rId);
        role.setrName(rName);
        role.setrPermission(generateHexStrByArray(permissions));
        roleService.updateRole(role);
        return "redirect:/roleManagement";
    }
}
