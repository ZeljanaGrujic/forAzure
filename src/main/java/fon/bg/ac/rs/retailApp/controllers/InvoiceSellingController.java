package fon.bg.ac.rs.retailApp.controllers;

import fon.bg.ac.rs.retailApp.dtos.ClientDto;
import fon.bg.ac.rs.retailApp.dtos.InvoiceSellingDto;
import fon.bg.ac.rs.retailApp.dtos.InvoiceStatusDto;
import fon.bg.ac.rs.retailApp.dtos.TextileDto;
import fon.bg.ac.rs.retailApp.models.Client;
import fon.bg.ac.rs.retailApp.models.InvoiceItem;
import fon.bg.ac.rs.retailApp.models.InvoiceSelling;
import fon.bg.ac.rs.retailApp.models.InvoiceStatus;
import fon.bg.ac.rs.retailApp.servicesImpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class InvoiceSellingController {

    @Autowired
    private InvoiceSellingServiceImpl invoiceSellingServiceImpl;

    @Autowired
    private ClientServiceImpl clientServiceImpl;

    @Autowired
    private TextileServiceImpl textileServiceImpl;

    @Autowired
    private InvoiceStatusServiceImpl invoiceStatusServiceImpl;

    @Autowired
    private InvoiceItemServiceImpl invoiceItemServiceImpl;

    @GetMapping("/invoicesSelling")
    public String getInvoicesSelling(Model model) {

        List<InvoiceSellingDto> invoiceSellings = invoiceSellingServiceImpl.getInvoicesSelling();
        List<ClientDto> clients = clientServiceImpl.getClients();
        List<InvoiceStatusDto> invoiceStatuses = invoiceStatusServiceImpl.getInvoiceStatuses();
        System.out.println(invoiceSellings);
        System.out.println(clients);
//        System.out.println(invoiceStatuses);
        if (invoiceSellings.isEmpty()) {
            model.addAttribute("invoiceSellings", null);
        } else {
            model.addAttribute("invoiceSellings", invoiceSellings);
        }

        model.addAttribute("clients", clients);
        model.addAttribute("invoiceStatuses", invoiceStatuses);
        //ovaj model saljem ka HTML stranici
        return "InvoiceSelling";
    }

    @PostMapping("/invoicesSelling/addNew")
    public String addBew(InvoiceSellingDto invoiceSelling) {
        InvoiceSellingDto saved = invoiceSellingServiceImpl.saveInvoiceSelling(invoiceSelling);
        System.out.println(saved.getId());
        return "redirect:/invoicesSelling";
    }

    @RequestMapping(value = "/invoicesSelling/findById/", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public InvoiceSellingDto findById(@RequestParam("id") Integer id) {
        InvoiceSellingDto invoice = invoiceSellingServiceImpl.findById(id);
        System.out.println(invoice);
        return invoiceSellingServiceImpl.findById(id);
    }

    @RequestMapping(value = "/invoicesSelling/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(InvoiceSellingDto invoiceSelling) {
        InvoiceSellingDto updated = invoiceSellingServiceImpl.saveInvoiceSelling(invoiceSelling);
        System.out.println(updated.getId());
        return "redirect:/invoicesSelling";
    }


//    @RequestMapping(value = "/clients/deleteById/", params = {"id"}, method = {RequestMethod.DELETE, RequestMethod.GET})
//    public String deleteById(@RequestParam("id") Integer id) {
////        Optional<Location> location = locationServiceImpl.findById(id);
////        System.out.println(country);
//        clientServiceImpl.deleteById(id);
//        return "redirect:/clients";
//    }


    @RequestMapping(value = "/invoice/textile/Edit/", params = {"id"}, method = RequestMethod.GET)
    public String editInvoice(@RequestParam Integer id, Model model) {
        InvoiceSellingDto invoiceSelling = invoiceSellingServiceImpl.findById(id);
        //isto mogu da ucitam listu iz invoiceitemsa preko find by id koja ce da vraca listu invojs itema gde je id onvojsa ovaj odavde
        //pa onda iz te liste pristupam preko textile i odatle citam jedinstveni broj i sta mi vec treba

        //trebace mi jedna add new u invoiceitems controlleru
        //moram da dodam atribute i relacije u invoice i invoice itemsima
        //i da napravim service i service impl za invoice itemse

        //u jednoj posebnoj listi, koja ce kasnije da bude sacuvana kao model, tj prosledjena kao model ka stranici ce biti ucitani svi itemi sa idjem ove fakure kojeg vec znam
        //ta metoda ce biti slicna sa get not invoice item
        //ili slicna metodi fill cities, kof regiona kad sam popunjavala
        //tako cu da dohvatim sve iteme jedne fakture
        List<TextileDto> textiles = textileServiceImpl.getTextiles();
        model.addAttribute("invoiceSelling", invoiceSelling);
        //ovde cu sad da ucitam proizvode preko idja ovog invoice sellinga koji je pronadjen
        //i poslacu ih kao items preko modela
        //ali cu posebno i da prodjem kroz listu i da lepo izracunam broj komada i ukupna cena i caaaaooooo i da ispisem to u posebnom redu

        List<InvoiceItem> invoiceItems = invoiceItemServiceImpl.findByInvoiceSellingId(invoiceSelling.getId());
        if (invoiceItems.isEmpty()) {
            model.addAttribute("invoiceItems", null);
            int invoiceCost = 0;
            model.addAttribute("invoiceCost", invoiceCost);
        } else {
            model.addAttribute("invoiceItems", invoiceItems);
            int invoiceCost = 0;
            for (InvoiceItem it : invoiceItems) {
                invoiceCost = invoiceCost + it.getTotalCost();
            }
            model.addAttribute("invoiceCost", invoiceCost);
        }

//        model.addAttribute("notInvoiceItems", textileServiceImpl.getNotInvoiceItems(invoiceSelling));

        model.addAttribute("textiles", textiles);
        return "invoiceSellingEdit";
    }

}
