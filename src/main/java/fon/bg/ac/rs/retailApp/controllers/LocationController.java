package fon.bg.ac.rs.retailApp.controllers;

import fon.bg.ac.rs.retailApp.dtos.CountryDto;
import fon.bg.ac.rs.retailApp.dtos.LocationDto;
import fon.bg.ac.rs.retailApp.models.Country;
import fon.bg.ac.rs.retailApp.models.Location;
import fon.bg.ac.rs.retailApp.servicesImpl.CountryServiceImpl;
import fon.bg.ac.rs.retailApp.servicesImpl.LocationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class LocationController {

    @Autowired
    private LocationServiceImpl locationServiceImpl;

    @Autowired
    private CountryServiceImpl countryServiceImpl;
    @GetMapping("/locations")
    public String getLocations(Model model) {

        List<LocationDto> locations = locationServiceImpl.getLocations();
        List<CountryDto> countries=countryServiceImpl.getCountries();
        System.out.println(locations);
        System.out.println(countries);
        if(locations.isEmpty()){
            model.addAttribute("locations", null);
        }else {
            model.addAttribute("locations", locations);
        }
        model.addAttribute("countries", countries);
        //ovaj model saljem ka HTML stranici
        return "Location";
    }

    @PostMapping("/locations/addNew")
    public String addBew(LocationDto location) {
        LocationDto savedLocation = locationServiceImpl.saveLocation(location);
        System.out.println(savedLocation.getId());
        return "redirect:/locations";
    }

    @RequestMapping(value = "/locations/findById/", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public LocationDto findById(@RequestParam("id") Integer id) {
        LocationDto location = locationServiceImpl.findById(id);
        System.out.println(location);
        return locationServiceImpl.findById(id);
    }

    @RequestMapping(value = "/locations/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(LocationDto location) {
        LocationDto updatedLocation = locationServiceImpl.saveLocation(location);
        System.out.println(updatedLocation.getId());
        return "redirect:/locations";
    }


    @RequestMapping(value = "/locations/deleteById/", params = {"id"}, method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteById(@RequestParam("id") Integer id) {

        try {
            locationServiceImpl.deleteById(id);
        }catch (Exception e){
            System.out.println("Ne mozete izbrisati podatke za ovu lokaciju");
            return "LocationDeleteError";
        }
        return "redirect:/locations";
    }


}
