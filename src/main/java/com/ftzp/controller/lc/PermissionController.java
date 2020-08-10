package com.ftzp.controller.lc;

import com.ftzp.pojo.lc.Permission;
import com.ftzp.service.lc.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Resource(name = "permissionService")
    PermissionService permissionService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    List<Permission> getPermission() {
        return permissionService.getPermission((Integer) null);
    }

    @RequestMapping(value = "/delete/{pId}", method = RequestMethod.GET)
    String deletePermission(@PathVariable Integer pId) {
        permissionService.deletePermission(pId);
        return "redirect:/permissionManagement";
    }

    @RequestMapping(value = "/get/{rPermission}", method = RequestMethod.GET)
    @ResponseBody
    List<Permission> getPermission(@PathVariable String rPermission) {
        return permissionService.getPermission(rPermission);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    String insertPermission(@RequestParam("pName") String pName, @RequestParam("pSrc") String pSrc) {
        Permission permission = new Permission();
        permission.setpName(pName);
        permission.setpSrc(pSrc);
        permissionService.insertPermission(permission);
        return "redirect:/permissionManagement";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    String updatePermission(@RequestParam("pId") Integer pId, @RequestParam("pName") String pName, @RequestParam("pSrc") String pSrc) {
        Permission permission = new Permission();
        permission.setpId(pId);
        permission.setpName(pName);
        permission.setpSrc(pSrc);
        permissionService.updatePermission(permission);
        return "redirect:/permissionManagement";
    }

}
