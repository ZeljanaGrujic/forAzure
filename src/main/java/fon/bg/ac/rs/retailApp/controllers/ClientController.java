package fon.bg.ac.rs.retailApp.controllers;

import fon.bg.ac.rs.retailApp.dtos.ClientDto;
import fon.bg.ac.rs.retailApp.dtos.LocationDto;
import fon.bg.ac.rs.retailApp.servicesImpl.ClientServiceImpl;
import fon.bg.ac.rs.retailApp.servicesImpl.LocationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClientController {


    @Autowired
    public ClientServiceImpl clientServiceImpl;

    @Autowired
    public LocationServiceImpl locationServiceImpl;


    @GetMapping("/clients")
    public String getClients(Model model) {

        List<LocationDto> locations = locationServiceImpl.getLocations();
        List<ClientDto> clients = clientServiceImpl.getClients();
        System.out.println(locations);
        System.out.println(clients);
        model.addAttribute("locations", locations);
        if(clients.isEmpty()){
            model.addAttribute("clients", null);
        }else {
            model.addAttribute("clients", clients);
        }
        //ovaj model saljem ka HTML stranici
        return "Client";
    }

    @PostMapping("/clients/addNew")
    public String addBew(ClientDto client) {
        try {
            ClientDto saved = clientServiceImpl.saveClient(client);
            System.out.println(saved.getId());
        } catch (Exception e) {
            System.out.println("Klijent nije uspesno sacuvan!");
            return "ClientSaveError";
        }
        return "redirect:/clients";
    }

    @RequestMapping(value = "/clients/findById/", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public ClientDto findById(@RequestParam("id") Integer id) {
        ClientDto client = clientServiceImpl.findById(id);
        System.out.println(client);
        return clientServiceImpl.findById(id);
    }

    @RequestMapping(value = "/clients/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(ClientDto client) {
        ClientDto updated = clientServiceImpl.saveClient(client);
        System.out.println(updated.getId());
        return "redirect:/clients";
    }


    @RequestMapping(value = "/clients/deleteById/", params = {"id"}, method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteById(@RequestParam("id") Integer id) {
        try {
            clientServiceImpl.deleteById(id);
        } catch (Exception e) {
            System.out.println("Ne mozete izbrisati podatke za ovog klijenta");
            return "ClientDeleteError";
        }

        return "redirect:/clients";
    }


}
