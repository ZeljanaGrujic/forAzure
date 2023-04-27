package fon.bg.ac.rs.retailApp.security.controllers;

import fon.bg.ac.rs.retailApp.models.User;
import fon.bg.ac.rs.retailApp.security.models.Role;
import fon.bg.ac.rs.retailApp.security.servicesImpl.RoleServiceImpl;
import fon.bg.ac.rs.retailApp.servicesImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class RoleController {
    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/roles")
    public String getRoles(Model model) {

        List<Role> roles = roleServiceImpl.getRoles();
        System.out.println(roles);
        model.addAttribute("roles", roles);
        //ovaj model saljem ka HTML stranici
        return "Role";
    }

    @PostMapping("/roles/addNew")
    public String addBew(Role role) {
        Role saved = roleServiceImpl.saveRole(role);
        System.out.println(saved.getId());
        return "redirect:/roles";
    }

    @RequestMapping(value = "/roles/findById/", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public Optional<Role> findById(@RequestParam("id") Integer id) {
        Optional<Role> role = roleServiceImpl.findById(id);
        System.out.println(role);
        return roleServiceImpl.findById(id);
    }

    @RequestMapping(value = "/roles/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(Role role) {
        Role updated = roleServiceImpl.saveRole(role);
        System.out.println(updated.getId());
        return "redirect:/roles";
    }


    @RequestMapping(value = "/roles/deleteById/", params = {"id"}, method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteById(@RequestParam("id") Integer id) {
//        Optional<Location> location = locationServiceImpl.findById(id);
//        System.out.println(country);
        roleServiceImpl.deleteById(id);
        return "redirect:/roles";
    }

    @RequestMapping(value = "/security/user/Edit/", params = {"id"},method = RequestMethod.GET)
    public String editUser(@RequestParam Integer id, Model model){
        User user = userServiceImpl.findById(id).orElse(null);
        model.addAttribute("user", user);
        model.addAttribute("userRoles", roleServiceImpl.getUserRoles(user));
        model.addAttribute("userNotRoles", roleServiceImpl.getUserNotRoles(user));
        return "userEdit1";
    }

    @RequestMapping(value="/security/role/assign/{userId}/{roleId}")
    public String assignRole(@PathVariable Integer userId,
                             @PathVariable Integer roleId){
        roleServiceImpl.assignUserRole(userId, roleId);
        return "redirect:/security/user/Edit/?id="+userId;
    }


    @RequestMapping("/security/role/unassign/{userId}/{roleId}")
    public String unassignRole(@PathVariable Integer userId,
                               @PathVariable Integer roleId){
        roleServiceImpl.unassignUserRole(userId, roleId);
        return "redirect:/security/user/Edit/?id="+userId;
    }
}
