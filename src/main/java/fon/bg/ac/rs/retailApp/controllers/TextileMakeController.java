package fon.bg.ac.rs.retailApp.controllers;

import fon.bg.ac.rs.retailApp.dtos.TextileMakeDto;
import fon.bg.ac.rs.retailApp.models.TextileMake;
import fon.bg.ac.rs.retailApp.servicesImpl.TextileMakeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class TextileMakeController {

    @Autowired
    private TextileMakeServiceImpl textileMakeServiceImpl;

    @GetMapping("/textileMakes")
    public String getTextileMakes(Model model) {

        List<TextileMakeDto> textileMakes = textileMakeServiceImpl.getTextileMakes();
        System.out.println(textileMakes);
        model.addAttribute("textileMakes", textileMakes);
        //ovaj model saljem ka HTML stranici
        return "TextileMake";
    }

    @PostMapping("/textileMakes/addNew")
    public String addBew(TextileMakeDto textileMake) {
        try {
            TextileMakeDto savedTextileMake = textileMakeServiceImpl.saveTextileMake(textileMake);
            System.out.println(savedTextileMake.getId());
        }catch (Exception e){
            System.out.println("Marka nije uspesno sacuvana!");
            return "TextileMakeSaveError";
        }
        return "redirect:/textileMakes";
    }

    @RequestMapping(value = "/textileMakes/findById/", params = {"id"}, method = RequestMethod.GET)
    @ResponseBody
    public TextileMakeDto findById(@RequestParam("id") Integer id) {
        TextileMakeDto textileMake = textileMakeServiceImpl.findById(id);
        System.out.println(textileMake);
        return textileMakeServiceImpl.findById(id);
    }

    @RequestMapping(value = "/textileMakes/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(TextileMakeDto textileMake) {
        TextileMakeDto updatedTextileMake = textileMakeServiceImpl.saveTextileMake(textileMake);
        System.out.println(updatedTextileMake.getId());
        return "redirect:/textileMakes";
    }


    @RequestMapping(value = "/textileMakes/deleteById/", params = {"id"}, method = {RequestMethod.DELETE, RequestMethod.GET})
    public String deleteById(@RequestParam("id") Integer id) {

        try {
            textileMakeServiceImpl.deleteById(id);
        }catch (Exception e){
            System.out.println("Ne mozete izbrisati podatke za ovu marku");
            return "TextileMakeDeleteError";
        }
        return "redirect:/textileMakes";
    }
}
