package fon.bg.ac.rs.retailApp.controllers;

import fon.bg.ac.rs.retailApp.dtos.EmployeeTypeDto;
import fon.bg.ac.rs.retailApp.models.EmployeeType;
import fon.bg.ac.rs.retailApp.servicesImpl.EmployeeTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeTypeController {

    @Autowired
    private EmployeeTypeServiceImpl employeeTypeServiceImpl;


    @GetMapping("/employeeTypes")
    public String getEmployeeTypes(Model model) {

        List<EmployeeTypeDto> employeeTypes = employeeTypeServiceImpl.getEmployeeTypes();
        System.out.println(employeeTypes);
        model.addAttribute("employeeTypes", employeeTypes);
        //ovaj model saljem ka HTML stranici
        return "EmployeeType";
    }

    @PostMapping("/employeeTypes/addNew")
    public String addBew(EmployeeTypeDto employeeType) {
        EmployeeTypeDto savedType = employeeTypeServiceImpl.saveEmployeeType(employeeType);
        System.out.println(savedType.getId());
        return "redirect:/employeeTypes";
    }

    @RequestMapping(value = "/employeeTypes/findById/", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public EmployeeTypeDto findById(@RequestParam("id") Integer id) {
        EmployeeTypeDto type = employeeTypeServiceImpl.findById(id);
        System.out.println(type);
        return employeeTypeServiceImpl.findById(id);
    }

    @RequestMapping(value = "/employeeTypes/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(EmployeeTypeDto employeeType) {
        EmployeeTypeDto updatedType = employeeTypeServiceImpl.saveEmployeeType(employeeType);
        System.out.println(updatedType.getId());
        return "redirect:/employeeTypes";
    }


    @RequestMapping(value = "/employeeTypes/deleteById/", params = {"id"}, method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteById(@RequestParam("id") Integer id) {
//        Optional<Location> location = locationServiceImpl.findById(id);
//        System.out.println(country);
        employeeTypeServiceImpl.deleteById(id);
        return "redirect:/employeeTypes";
    }


}
