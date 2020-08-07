package com.ftzp.controller.lc;


import com.ftzp.pojo.lc.Permission;
import com.ftzp.pojo.lc.Role;
import com.ftzp.pojo.lc.User;
import com.ftzp.service.lc.PermissionService;
import com.ftzp.service.lc.RoleService;
import com.ftzp.service.lc.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/u")
public class UserController {

    UserService userService;

    RoleService roleService;

    PermissionService permissionService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/goUserManager")
    String goToUserManager() {
        return "/lc/user/userManager";
    }

    @RequestMapping("/goRoleManager")
    String goRoleManager() {
        return "/lc/user/roleManager";
    }

    @RequestMapping("/goPermissionManager")
    String goPermissionManager() {
        return "/lc/user/permissionManager";
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    @ResponseBody
    List<User> getUser() {
        return userService.getUser(null);
    }

    @RequestMapping(value = "/deleteUser/{uId}", method = RequestMethod.GET)
    String deleteUser(@PathVariable Integer uId) {
        userService.deleteUser(uId);
        return "redirect:/u/goUserManager";
    }

    @RequestMapping(value = "/insertUser", method = RequestMethod.POST)
    String insertUser(@RequestParam("uName") String uName, @RequestParam("uPass") String uPass, @RequestParam("rId") Integer rId) {
        User user = new User();
        user.setrId(rId);
        user.setuName(uName);
        user.setuPass(uPass);
        userService.insertUser(user);
        return "redirect:/u/goUserManager";
    }

    @RequestMapping(value = "/getRole", method = RequestMethod.GET)
    @ResponseBody
    List<Role> getRole() {
        return roleService.getRole(null);
    }

    @RequestMapping(value = "/deleteRole/{rId}", method = RequestMethod.GET)
    String deleteRole(@PathVariable Integer rId) {
        roleService.deleteRole(rId);
        return "redirect:/u/goRoleManager";
    }

    @RequestMapping(value = "/insertRole", method = RequestMethod.POST)
    String insertRole(@RequestParam("rName") String rName, @RequestParam("rPermission") String rPermission) {
        Role role = new Role();
        role.setrName(rName);
        role.setrPermission(rPermission);
        roleService.insertRole(role);
        return "redirect:/u/goRoleManager";
    }

    @RequestMapping(value = "/updateRole", method = RequestMethod.POST)
    String updateRole(@RequestParam("rId") Integer rId, @RequestParam("rName") String rName, @RequestParam("rPermission") String rPermission) {
        Role role = new Role();
        role.setrId(rId);
        role.setrName(rName);
        role.setrPermission(rPermission);
        roleService.updateRole(role);
        return "redirect:/u/goRoleManager";
    }

    @RequestMapping(value = "/getPermission", method = RequestMethod.GET)
    @ResponseBody
    List<Permission> getPermission() {
        return permissionService.getPermission((Integer) null);
    }

    @RequestMapping(value = "/deletePermission/{pId}", method = RequestMethod.GET)
    String deletePermission(@PathVariable Integer pId) {
        permissionService.deletePermission(pId);
        return "redirect:/u/goPermissionManager";
    }

    @RequestMapping(value = "/getPermission/{rPermission}", method = RequestMethod.GET)
    @ResponseBody
    List<Permission> getPermission(@PathVariable String rPermission) {
        return permissionService.getPermission(rPermission);
    }

    @RequestMapping(value = "/insertPermission", method = RequestMethod.POST)
    String insertPermission(@RequestParam("pName") String pName, @RequestParam("pSrc") String pSrc) {
        Permission permission = new Permission();
        permission.setpName(pName);
        permission.setpSrc(pSrc);
        permissionService.insertPermission(permission);
        return "redirect:/u/goPermissionManager";
    }

    @RequestMapping(value = "/updatePermission", method = RequestMethod.POST)
    String updatePermission(@RequestParam("pId") Integer pId, @RequestParam("pName") String pName, @RequestParam("pSrc") String pSrc) {
        Permission permission = new Permission();
        permission.setpId(pId);
        permission.setpName(pName);
        permission.setpSrc(pSrc);
        permissionService.updatePermission(permission);
        return "redirect:/u/goPermissionManager";
    }

}
