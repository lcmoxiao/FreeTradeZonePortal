package com.ftzp.controller.lc.user;

import com.ftzp.cache.RedisObjCache;
import com.ftzp.pojo.lc.user.Permission;
import com.ftzp.service.lc.user.PermissionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.ftzp.controller.lc.LoginController.getRemoteIP;

@Controller
@RequestMapping("/permission")
public class PermissionController {

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
    String deletePermission(@PathVariable Integer pId) {
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
    String insertPermission(@RequestParam("pName") String pName, @RequestParam("pSrc") String pSrc) {
        Permission permission = new Permission();
        permission.setpName(pName);
        permission.setpSrc(pSrc);
        permissionService.insertPermission(permission);
        return "post success";
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    String updatePermission(@RequestParam("pId") Integer pId, @RequestParam("pName") String pName, @RequestParam("pSrc") String pSrc) {
        Permission permission = new Permission();
        permission.setpId(pId);
        permission.setpName(pName);
        permission.setpSrc(pSrc);
        permissionService.updatePermission(permission);
        return "update success";
    }

}
