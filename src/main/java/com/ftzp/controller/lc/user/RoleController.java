package com.ftzp.controller.lc.user;

import com.ftzp.pojo.lc.user.Role;
import com.ftzp.service.lc.user.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static com.ftzp.PermissionParseUtil.generateHexStrByArray;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Resource(name = "roleService")
    RoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    List<Role> getRole() {
        return roleService.getRole(null);
    }

    @RequestMapping(value = "/{rId}", method = RequestMethod.DELETE)
    @ResponseBody
    String deleteRole(@PathVariable Integer rId) {
        roleService.deleteRole(rId);
        return "redirect:/roleManagement";
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    String insertRole(@RequestParam("rName") String rName,
                      @RequestParam("permissions") String[] permissions) {
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
    String updateRole(@RequestParam("rId") Integer rId, @RequestParam("rName") String rName, @RequestParam("permissions") String[] permissions) {
        Role role = new Role();
        role.setrId(rId);
        role.setrName(rName);
        role.setrPermission(generateHexStrByArray(permissions));
        roleService.updateRole(role);
        return "redirect:/roleManagement";
    }
}
