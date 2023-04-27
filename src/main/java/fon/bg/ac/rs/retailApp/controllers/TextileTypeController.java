package fon.bg.ac.rs.retailApp.controllers;

import fon.bg.ac.rs.retailApp.dtos.TextileTypeDto;
import fon.bg.ac.rs.retailApp.servicesImpl.TextileTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TextileTypeController {


    @Autowired
    private TextileTypeServiceImpl textileTypeServiceImpl;

    @GetMapping("/textileTypes")
    public String getTextileTypes(Model model) {

        List<TextileTypeDto> textileTypes = textileTypeServiceImpl.getTextileTypes();
        System.out.println(textileTypes);
        model.addAttribute("textileTypes", textileTypes);
        //ovaj model saljem ka HTML stranici
        return "TextileType";
    }

    @PostMapping("/textileTypes/addNew")
    public String addBew(TextileTypeDto textileType) {
        try {
            TextileTypeDto savedTextileType = textileTypeServiceImpl.saveTextileType(textileType);
            System.out.println(savedTextileType.getId());
        }catch (Exception e){
            System.out.println("Kategorija proizvoda nije uspesno sacuvana!");
            return "TextileTypeSaveError";
        }
        return "redirect:/textileTypes";
    }

    @RequestMapping(value = "/textileTypes/findById/", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public TextileTypeDto findById(@RequestParam("id") Integer id) {
        TextileTypeDto textileType = textileTypeServiceImpl.findById(id);
        System.out.println(textileType);
        return textileTypeServiceImpl.findById(id);
    }

    @RequestMapping(value = "/textileTypes/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(TextileTypeDto textileType) {
        TextileTypeDto updatedTextileType = textileTypeServiceImpl.saveTextileType(textileType);
        System.out.println(updatedTextileType.getId());
        return "redirect:/textileTypes";
    }


    @RequestMapping(value = "/textileTypes/deleteById/", params = {"id"}, method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteById(@RequestParam("id") Integer id) {

        try {
            textileTypeServiceImpl.deleteById(id);
        }catch (Exception e){
            System.out.println("Ne mozete izbrisati podatke za ovog klijenta");
            return "TextileTypeDeleteError";
        }
        return "redirect:/textileTypes";
    }

}
