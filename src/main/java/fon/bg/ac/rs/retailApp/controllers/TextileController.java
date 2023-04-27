package fon.bg.ac.rs.retailApp.controllers;

import fon.bg.ac.rs.retailApp.dtos.*;
import fon.bg.ac.rs.retailApp.models.*;
import fon.bg.ac.rs.retailApp.servicesImpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class TextileController {

    @Autowired
    private TextileServiceImpl textileServiceImpl;
    @Autowired
    private TextileTypeServiceImpl textileTypeServiceImpl;

    @Autowired
    private TextileModelServiceImpl textileModelServiceImpl;

    @Autowired
    private TextileMakeServiceImpl textileMakeServiceImpl;

    @Autowired
    private TextileStatusServiceImpl textileStatusServiceImpl;

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @Autowired
    private SupplierServiceImpl supplierServiceImpl;

    @Autowired
    private InvoiceSellingServiceImpl invoiceSellingServiceImpl;
    @GetMapping("/textiles")
    public String getTextiles(Model model) {

        List<TextileDto> textiles = textileServiceImpl.getTextiles();
        List<TextileTypeDto> textileTypes=textileTypeServiceImpl.getTextileTypes();
        List<TextileMakeDto> textileMakes = textileMakeServiceImpl.getTextileMakes();
        List<TextileModelDto> textileModels=textileModelServiceImpl.getTextileModels();
        List<TextileStatusDto> textileStatuses = textileStatusServiceImpl.getTextileStatuses();
        List<Employee> employees=employeeServiceImpl.getEmployees();
        List<SupplierDto> suppliers = supplierServiceImpl.getSuppliers();
        System.out.println(textiles);
        System.out.println(textileTypes);
        System.out.println(textileMakes);
        System.out.println(textileModels);
        System.out.println(textileStatuses);
        System.out.println(employees);
        System.out.println(suppliers);
//        System.out.println("GORNJI DELOVI");
//        System.out.println(getUpperBodyTextiles());
//        System.out.println("DONJI DELOVI");
//        System.out.println(getLowerBodyTextiles());
        model.addAttribute("textiles", textiles);
        model.addAttribute("textileTypes", textileTypes);
        model.addAttribute("textileMakes", textileMakes);
        model.addAttribute("textileModels", textileModels);
        model.addAttribute("textileStatuses", textileStatuses);
        model.addAttribute("employees", employees);
        model.addAttribute("suppliers", suppliers);
        //ovaj model saljem ka HTML stranici
        return "Textile";
    }


    @GetMapping("/textilesUser")
    public String getTextilesUser(Model model) {

        List<TextileDto> textiles = textileServiceImpl.getTextiles();
        List<TextileTypeDto> textileTypes=textileTypeServiceImpl.getTextileTypes();
        List<TextileMakeDto> textileMakes = textileMakeServiceImpl.getTextileMakes();
        List<TextileModelDto> textileModels=textileModelServiceImpl.getTextileModels();
        List<TextileStatusDto> textileStatuses = textileStatusServiceImpl.getTextileStatuses();
        List<Employee> employees=employeeServiceImpl.getEmployees();
        List<SupplierDto> suppliers = supplierServiceImpl.getSuppliers();
        System.out.println(textiles);
        System.out.println(textileTypes);
        System.out.println(textileMakes);
        System.out.println(textileModels);
        System.out.println(textileStatuses);
        System.out.println(employees);
        System.out.println(suppliers);
//        System.out.println("GORNJI DELOVI");
//        System.out.println(getUpperBodyTextiles());
//        System.out.println("DONJI DELOVI");
//        System.out.println(getLowerBodyTextiles());
        model.addAttribute("textiles", textiles);
        model.addAttribute("textileTypes", textileTypes);
        model.addAttribute("textileMakes", textileMakes);
        model.addAttribute("textileModels", textileModels);
        model.addAttribute("textileStatuses", textileStatuses);
        model.addAttribute("employees", employees);
        model.addAttribute("suppliers", suppliers);
        //ovaj model saljem ka HTML stranici
        return "TextileUser";
    }

    @PostMapping("/textiles/addNew")
    public String addBew(TextileDto textile) {
        try {
            TextileDto saved = textileServiceImpl.saveTextile(textile);
            System.out.println(saved.getId());
        }catch (Exception e){
            System.out.println("Robni proizvod nije uspesno sacuvan!");
            return "TextileSaveError";
        }
        return "redirect:/textiles";
    }

    @RequestMapping(value = "/textiles/findById/", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public TextileDto findById(@RequestParam("id") Integer id) {
        TextileDto textile = textileServiceImpl.findById(id);
        System.out.println(textile);
        return textileServiceImpl.findById(id);
    }

    @RequestMapping(value = "/textiles/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(TextileDto textile) {
        TextileDto updated = textileServiceImpl.saveTextile(textile);
        System.out.println(updated.getId());
        return "redirect:/textiles";
    }


    @RequestMapping(value = "/textiles/deleteById/", params = {"id"}, method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteById(@RequestParam("id") Integer id) {

        try {
            textileServiceImpl.deleteById(id);
        }catch (Exception e){
            System.out.println("Ne mozete izbrisati podatke za ovaj robni proizvod");
            return "TextileDeleteError";
        }
        return "redirect:/textiles";
    }

    private List<TextileDto> getUpperBodyTextiles(){
        List<TextileDto> upperBodyTextiles=textileServiceImpl.findByPurpose("Gornji deo");
        List<TextileDto> textilesWithPicture= new ArrayList<>();

        for (TextileDto t:upperBodyTextiles) {
            if(t.getPhoto()!=null){
                textilesWithPicture.add(t);
            }
        }
        if(textilesWithPicture.isEmpty()){
            textilesWithPicture=upperBodyTextiles;
        }
        return textilesWithPicture;
    }

    private List<TextileDto> getLowerBodyTextiles(){
        List<TextileDto> lowerBodyTextiles=textileServiceImpl.findByPurpose("Donji deo");

        List<TextileDto> textilesWithPicture= new ArrayList<>();

        for (TextileDto t:lowerBodyTextiles) {
            if(t.getPhoto()!=null){
                textilesWithPicture.add(t);
            }
        }
        if(textilesWithPicture.isEmpty()){
            textilesWithPicture=lowerBodyTextiles;
        }


        return textilesWithPicture;
    }


    @GetMapping("/upperBodyTextiles")
    public String getUpperBodyTextiles(Model model) {

        List<TextileDto> textiles = getUpperBodyTextiles();
//        System.out.println("GORNJI DELOVI");
//        System.out.println(getUpperBodyTextiles());
//        System.out.println("DONJI DELOVI");
//        System.out.println(getLowerBodyTextiles());
        model.addAttribute("textiles", textiles);
        //ovaj model saljem ka HTML stranici
        return "UpperBodyTextile";
    }

    @GetMapping("/lowerBodyTextiles")
    public String getLowerBodyTextiles(Model model) {

        List<TextileDto> textiles = getLowerBodyTextiles();
//        System.out.println("GORNJI DELOVI");
//        System.out.println(getUpperBodyTextiles());
//        System.out.println("DONJI DELOVI");
//        System.out.println(getLowerBodyTextiles());
        model.addAttribute("textiles", textiles);
        //ovaj model saljem ka HTML stranici
        return "UpperBodyTextile";
    }


//    @RequestMapping(value="/invoice/textile/add/{invoiceSellingId}/{textileId}")
//    public String addItem(@PathVariable Integer invoiceSellingId,
//                             @PathVariable Integer textileId){
//        textileServiceImpl.addTextileItem(invoiceSellingId, textileId);
//        return "redirect:/invoice/textile/Edit/?id="+invoiceSellingId;
//    }


//    @RequestMapping("/invoice/textile/remove/{invoiceSellingId}/{textileId}")
//    public String removeItem(@PathVariable Integer invoiceSellingId,
//                               @PathVariable Integer textileId){
//        textileServiceImpl.removeTextileItem(invoiceSellingId, textileId);
//        return "redirect:/invoice/textile/Edit/?id="+invoiceSellingId;
//    }
}
