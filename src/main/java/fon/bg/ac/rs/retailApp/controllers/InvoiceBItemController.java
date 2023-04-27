package fon.bg.ac.rs.retailApp.controllers;

import fon.bg.ac.rs.retailApp.dtos.InvoiceBItemDto;
import fon.bg.ac.rs.retailApp.dtos.InvoiceItemDto;
import fon.bg.ac.rs.retailApp.servicesImpl.InvoiceBItemServiceImpl;
import fon.bg.ac.rs.retailApp.servicesImpl.InvoiceItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InvoiceBItemController {

    @Autowired
    private InvoiceBItemServiceImpl invoiceBItemServiceImpl;

    @PostMapping("/invoiceBItems/addNew")
    public String addBew(InvoiceBItemDto invoiceItem) {
        InvoiceBItemDto saved = invoiceBItemServiceImpl.saveInvoiceItem(invoiceItem);
        System.out.println("INVOICE SELLING ID ********************" + saved.getInvoicebuyingid());
        return "redirect:/invoiceb/textile/Edit/?id=" + saved.getInvoicebuyingid();
    }


    @RequestMapping(value = "/invoiceb/textile/remove/", params = {"id"}, method = {RequestMethod.DELETE, RequestMethod.GET})
    public String removeItem(@RequestParam("id") Integer id) {
        InvoiceBItemDto item = invoiceBItemServiceImpl.findById(id);
        int invoiceId = item.getInvoicebuyingid();
        System.out.println("*********INVOICE DELETE ID*********" + invoiceId);
        invoiceBItemServiceImpl.deleteById(id);
        return "redirect:/invoiceb/textile/Edit/?id=" + invoiceId;
    }

    @RequestMapping(value = "/invoiceBItem/deleteById/", params = {"id"}, method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteById(@RequestParam("id") Integer id) {
        InvoiceBItemDto item = invoiceBItemServiceImpl.findById(id);
        int invoiceId = item.getInvoicebuyingid();
        System.out.println("ID INVOJSA ************" + invoiceId);
        try {
            invoiceBItemServiceImpl.deleteById(id);
        } catch (Exception e) {
            System.out.println("Ne mozete izbrisati podatke za ovaj proizvod sa fakture");
            return "error";
        }

        return "redirect:/invoiceb/textile/Edit/?id=" + invoiceId;
    }
}
