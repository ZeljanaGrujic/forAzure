package fon.bg.ac.rs.retailApp.controllers;

import fon.bg.ac.rs.retailApp.dtos.InvoiceStatusDto;
import fon.bg.ac.rs.retailApp.models.InvoiceStatus;
import fon.bg.ac.rs.retailApp.servicesImpl.InvoiceStatusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class InvoiceStatusController {

    @Autowired
    private InvoiceStatusServiceImpl invoiceStatusServiceImpl;

    @GetMapping("/invoiceStatuses")
    public String getInvoiceStatus(Model model) {

        List<InvoiceStatusDto> invoiceStatuses = invoiceStatusServiceImpl.getInvoiceStatuses();
        System.out.println(invoiceStatuses);
        model.addAttribute("invoiceStatuses", invoiceStatuses);
        //ovaj model saljem ka HTML stranici
        return "InvoiceStatus";
    }
    @PostMapping("/invoiceStatuses/addNew")
    public String addBew(InvoiceStatusDto invoiceStatus) {
        InvoiceStatusDto savedinvoiceStatuses = invoiceStatusServiceImpl.saveInvoiceStatus(invoiceStatus);
        System.out.println(invoiceStatus.getId());
        return "redirect:/invoiceStatuses";
    }

    @RequestMapping(value = "/invoiceStatuses/findById/", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public InvoiceStatusDto findById(@RequestParam("id") Integer id) {
        InvoiceStatusDto invoiceStatus = invoiceStatusServiceImpl.findById(id);
        System.out.println(invoiceStatus);
        return invoiceStatusServiceImpl.findById(id);
    }
}
