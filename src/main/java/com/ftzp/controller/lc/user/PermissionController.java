package com.ftzp.controller.lc.user;

import com.ftzp.cache.RedisObjCache;
import com.ftzp.controller.lc.LoginController;
import com.ftzp.pojo.lc.user.Permission;
import com.ftzp.pojo.lc.user.User;
import com.ftzp.service.lc.user.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.ftzp.controller.lc.LoginController.getRemoteIP;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);

    @Resource(name = "redisObjCache")
    RedisObjCache redisObjCache;
    @Resource(name = "permissionService")
    PermissionService permissionService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    List<Permission> getPermission() {
        return permissionService.getPermission((Integer) null);
    }

    @RequestMapping(value = "/{pId}", method = RequestMethod.DELETE)
    @ResponseBody
    String deletePermission(@PathVariable Integer pId, HttpServletRequest re) {
        User u = (User) redisObjCache.getValue(getRemoteIP(re) + "u");
        logger.info(u.getuName() + "删除了权限pId：" + pId);
        permissionService.deletePermission(pId);
        return "delete p success";
    }

    @RequestMapping(value = "/{rPermission}", method = RequestMethod.GET)
    @ResponseBody
    List<Permission> getPermission(@PathVariable String rPermission) {
        return permissionService.getPermission(rPermission);
    }

    @RequestMapping(value = "/mine", method = RequestMethod.GET)
    @ResponseBody
    List<Permission> getPermission(HttpServletRequest re) {
        String IP = getRemoteIP(re);
        return (List<Permission>) redisObjCache.getValue(IP + "p");
    }


    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    String insertPermission(@RequestParam("pName") String pName, @RequestParam("pSrc") String pSrc, HttpServletRequest re) {
        Permission permission = new Permission();
        permission.setpName(pName);
        permission.setpSrc(pSrc);
        permissionService.insertPermission(permission);
        User u = (User) redisObjCache.getValue(getRemoteIP(re) + "u");
        logger.info(u.getuName() + "增加了权限pName：" + pName);
        return "post success";
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    String updatePermission(@RequestParam("pId") Integer pId,
                            @RequestParam("pName") String pName,
                            @RequestParam("pSrc") String pSrc,
                            HttpServletRequest re) {
        User u = (User) redisObjCache.getValue(getRemoteIP(re) + "u");
        logger.info(u.getuName() + "更新了权限pId：" + pId);
        Permission permission = new Permission();
        permission.setpId(pId);
        permission.setpName(pName);
        permission.setpSrc(pSrc);
        permissionService.updatePermission(permission);
        return "update success";
    }

}
