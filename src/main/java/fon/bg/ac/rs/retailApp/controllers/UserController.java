package fon.bg.ac.rs.retailApp.controllers;

import fon.bg.ac.rs.retailApp.models.Contact;
import fon.bg.ac.rs.retailApp.models.User;
import fon.bg.ac.rs.retailApp.servicesImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;
    @GetMapping("/users")
    public String getUsers(Model model) {

        List<User> users=userServiceImpl.getUsers();
        if(users.isEmpty()){
            model.addAttribute("users", null);
        }else {
            System.out.println(users);
            model.addAttribute("users", users);
        }
        //ovaj model saljem ka HTML stranici

        return "User";
    }

    @PostMapping(value="users/addNew")
    public RedirectView addNew(User user) {
        User saved= userServiceImpl.saveUser(user);
        System.out.println(saved);
        RedirectView  redirectView= new RedirectView("/login",true);
//        redir.addFlashAttribute("message",
//                "You successfully registered! You can now login");
        return redirectView;
    }


    @RequestMapping(value = "/users/findById/", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public Optional<User> findById(@RequestParam("id") Integer id) {
        Optional<User> user = userServiceImpl.findById(id);
        System.out.println(user);
        return userServiceImpl.findById(id);
    }

    @RequestMapping(value = "/users/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(User user) {
        User updated = userServiceImpl.saveUser(user);
        System.out.println(updated.getId());
        return "redirect:/users";
    }


    @RequestMapping(value = "/users/deleteById/", params = {"id"}, method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteById(@RequestParam("id") Integer id) {
//        Optional<Location> location = locationServiceImpl.findById(id);
//        System.out.println(country);
        userServiceImpl.deleteById(id);
        return "redirect:/users";
    }
}
