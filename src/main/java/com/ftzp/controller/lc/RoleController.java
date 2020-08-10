package com.ftzp.controller.lc;

import com.ftzp.pojo.lc.Role;
import com.ftzp.service.lc.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }


    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    List<Role> getRole() {
        return roleService.getRole(null);
    }

    @RequestMapping(value = "/delete/{rId}", method = RequestMethod.GET)
    String deleteRole(@PathVariable Integer rId) {
        roleService.deleteRole(rId);
        return "redirect:/roleManagement";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    String insertRole(@RequestParam("rName") String rName, @RequestParam("rPermission") String rPermission) {
        Role role = new Role();
        role.setrName(rName);
        role.setrPermission(rPermission);
        roleService.insertRole(role);
        return "redirect:/roleManagement";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    String updateRole(@RequestParam("rId") Integer rId, @RequestParam("rName") String rName, @RequestParam("rPermission") String rPermission) {
        Role role = new Role();
        role.setrId(rId);
        role.setrName(rName);
        role.setrPermission(rPermission);
        roleService.updateRole(role);
        return "redirect:/roleManagement";
    }

}
