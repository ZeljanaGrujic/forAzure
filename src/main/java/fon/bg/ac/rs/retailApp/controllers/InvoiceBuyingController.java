package fon.bg.ac.rs.retailApp.controllers;

import fon.bg.ac.rs.retailApp.dtos.*;
import fon.bg.ac.rs.retailApp.models.*;
import fon.bg.ac.rs.retailApp.servicesImpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class InvoiceBuyingController {

    @Autowired
    private SupplierServiceImpl supplierServiceImpl;

    @Autowired
    private InvoiceBuyingServiceImpl invoiceBuyingServiceImpl;

    @Autowired
    private InvoiceStatusServiceImpl invoiceStatusServiceImpl;

    @Autowired
    private TextileServiceImpl textileServiceImpl;

    @Autowired
    private InvoiceItemServiceImpl invoiceItemServiceImpl;

    @Autowired
    private InvoiceBItemServiceImpl invoiceBItemServiceImpl;


    @GetMapping("/invoicesBuying")
    public String getInvoicesBuying(Model model) {

        List<InvoiceBuyingDto> invoiceBuyings = invoiceBuyingServiceImpl.getInvoicesBuying();
        List<SupplierDto> suppliers=supplierServiceImpl.getSuppliers();
        List<InvoiceStatusDto>invoiceStatuses=invoiceStatusServiceImpl.getInvoiceStatuses();
//        List<TextileDto>textiles=textileServiceImpl.getTextiles();
        System.out.println(invoiceBuyings);
        System.out.println(suppliers);
        System.out.println(invoiceStatuses);
        if(invoiceBuyings.isEmpty()){
            model.addAttribute("invoiceBuyings", null);
        }else{
            model.addAttribute("invoiceBuyings", invoiceBuyings);
        }

        model.addAttribute("suppliers", suppliers);
        model.addAttribute("invoiceStatuses", invoiceStatuses);
//        model.addAttribute("textiles", textiles);
        //ovaj model saljem ka HTML stranici
        return "InvoiceBuying";
    }

    @PostMapping("/invoicesBuying/addNew")
    public String addBew(InvoiceBuyingDto invoiceBuying) {
        InvoiceBuyingDto saved = invoiceBuyingServiceImpl.saveInvoiceBuying(invoiceBuying);
        System.out.println(saved.getId());
        return "redirect:/invoicesBuying";
    }

    @RequestMapping(value = "/invoicesBuying/findById/", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public InvoiceBuyingDto findById(@RequestParam("id") Integer id) {
        InvoiceBuyingDto invoiceBuying = invoiceBuyingServiceImpl.findById(id);
        System.out.println(invoiceBuying);
        return invoiceBuyingServiceImpl.findById(id);
    }

    @RequestMapping(value = "/invoicesBuying/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(InvoiceBuyingDto invoiceBuying) {
        InvoiceBuyingDto updated = invoiceBuyingServiceImpl.saveInvoiceBuying(invoiceBuying);
        System.out.println(updated.getId());
        return "redirect:/invoicesBuying";
    }


//    @RequestMapping(value = "/suppliers/deleteById/", params = {"id"}, method = {RequestMethod.DELETE, RequestMethod.GET})
//    public String deleteById(@RequestParam("id") Integer id) {
////        Optional<Location> location = locationServiceImpl.findById(id);
////        System.out.println(country);
//        supplierServiceImpl.deleteById(id);
//        return "redirect:/suppliers";
//    }




    @RequestMapping(value = "/invoiceb/textile/Edit/", params = {"id"}, method = RequestMethod.GET)
    public String editInvoice(@RequestParam Integer id, Model model) {
        InvoiceBuyingDto invoiceBuying = invoiceBuyingServiceImpl.findById(id);
        List<TextileDto> textiles = textileServiceImpl.getTextiles();
        model.addAttribute("invoiceBuying", invoiceBuying);
        //ovde cu sad da ucitam proizvode preko idja ovog invoice sellinga koji je pronadjen
        //i poslacu ih kao items preko modela
        //ali cu posebno i da prodjem kroz listu i da lepo izracunam broj komada i ukupna cena i caaaaooooo i da ispisem to u posebnom redu

        List<InvoiceBItem> invoiceItems = invoiceBItemServiceImpl.findByInvoiceBuyingId(invoiceBuying.getId());
        if (invoiceItems.isEmpty()) {
            model.addAttribute("invoiceItems", null);
            int invoiceCost = 0;
            model.addAttribute("invoiceCost", invoiceCost);
        } else {
            model.addAttribute("invoiceItems", invoiceItems);
            int invoiceCost = 0;
            for (InvoiceBItem it : invoiceItems) {
                invoiceCost = invoiceCost + it.getTotalCost();
            }
            model.addAttribute("invoiceCost", invoiceCost);
        }

//        model.addAttribute("notInvoiceItems", textileServiceImpl.getNotInvoiceItems(invoiceSelling));

        model.addAttribute("textiles", textiles);
        return "invoiceBuyingEdit";
    }

}
