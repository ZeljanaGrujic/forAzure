package fon.bg.ac.rs.retailApp.controllers;

import fon.bg.ac.rs.retailApp.dtos.InvoiceItemDto;
import fon.bg.ac.rs.retailApp.dtos.InvoiceSellingDto;
import fon.bg.ac.rs.retailApp.models.InvoiceItem;
import fon.bg.ac.rs.retailApp.servicesImpl.InvoiceItemServiceImpl;
import fon.bg.ac.rs.retailApp.servicesImpl.InvoiceSellingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InvoiceItemController {

    @Autowired
    private InvoiceItemServiceImpl invoiceItemServiceImpl;

    @PostMapping("/invoiceItems/addNew")
    public String addBew(InvoiceItemDto invoiceItem) {
        InvoiceItemDto saved = invoiceItemServiceImpl.saveInvoiceItem(invoiceItem);
        System.out.println("INVOICE SELLING ID ********************" + saved.getInvoicesellingid());
        return "redirect:/invoice/textile/Edit/?id=" + saved.getInvoicesellingid();
    }


    @RequestMapping(value = "/invoice/textile/remove/", params = {"id"}, method = {RequestMethod.DELETE, RequestMethod.GET})
    public String removeItem(@RequestParam("id") Integer id) {
        InvoiceItemDto item = invoiceItemServiceImpl.findById(id);
        int invoiceId = item.getInvoicesellingid();
        System.out.println("*********INVOICE DELETE ID*********" + invoiceId);
        invoiceItemServiceImpl.deleteById(id);
        return "redirect:/invoice/textile/Edit/?id=" + invoiceId;
    }

    @RequestMapping(value = "/invoiceItem/deleteById/", params = {"id"}, method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteById(@RequestParam("id") Integer id) {
        InvoiceItemDto item = invoiceItemServiceImpl.findById(id);
        int invoiceId = item.getInvoicesellingid();
        System.out.println("ID INVOJSA ************" + invoiceId);
        try {
            invoiceItemServiceImpl.deleteById(id);
        } catch (Exception e) {
            System.out.println("Ne mozete izbrisati podatke za ovaj proizvod sa fakture");
            return "error";
        }

        return "redirect:/invoice/textile/Edit/?id=" + invoiceId;
    }
}
