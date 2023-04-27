package fon.bg.ac.rs.retailApp.controllers;

import fon.bg.ac.rs.retailApp.dtos.LocationDto;
import fon.bg.ac.rs.retailApp.dtos.SupplierDto;
import fon.bg.ac.rs.retailApp.models.Location;
import fon.bg.ac.rs.retailApp.models.Supplier;
import fon.bg.ac.rs.retailApp.servicesImpl.LocationServiceImpl;
import fon.bg.ac.rs.retailApp.servicesImpl.SupplierServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class SupplierController {

    @Autowired
    private SupplierServiceImpl supplierServiceImpl;

    @Autowired
    private LocationServiceImpl locationServiceImpl;


    @GetMapping("/suppliers")
    public String getSuppliers(Model model) {

        List<LocationDto> locations = locationServiceImpl.getLocations();
        List<SupplierDto> suppliers=supplierServiceImpl.getSuppliers();
        System.out.println(locations);
        System.out.println(suppliers);
        model.addAttribute("locations", locations);
        if(suppliers.isEmpty()){
            model.addAttribute("suppliers", null);
        }else {
            model.addAttribute("suppliers", suppliers);
        }
        //ovaj model saljem ka HTML stranici
        return "Supplier";
    }

    @PostMapping("/suppliers/addNew")
    public String addBew(SupplierDto supplier) {
        SupplierDto saved = supplierServiceImpl.saveSupplier(supplier);
        System.out.println(saved.getId());
        return "redirect:/suppliers";
    }

    @RequestMapping(value = "/suppliers/findById/", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public SupplierDto findById(@RequestParam("id") Integer id) {
        SupplierDto supplier = supplierServiceImpl.findById(id);
        System.out.println(supplier);
        return supplierServiceImpl.findById(id);
    }

    @RequestMapping(value = "/suppliers/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(SupplierDto supplier) {
        SupplierDto updated = supplierServiceImpl.saveSupplier(supplier);
        System.out.println(updated.getId());
        return "redirect:/suppliers";
    }


    @RequestMapping(value = "/suppliers/deleteById/", params = {"id"}, method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteById(@RequestParam("id") Integer id) {

        try {
            supplierServiceImpl.deleteById(id);
        }catch (Exception e){
            System.out.println("Ne mozete izbrisati podatke za ovog dobavljaca");
            return "SupplierDeleteError";
        }
        return "redirect:/suppliers";
    }

}
