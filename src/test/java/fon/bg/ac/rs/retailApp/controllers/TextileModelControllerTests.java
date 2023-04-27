package fon.bg.ac.rs.retailApp.controllers;

import fon.bg.ac.rs.retailApp.dtos.TextileModelDto;
import fon.bg.ac.rs.retailApp.servicesImpl.TextileModelServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TextileModelControllerTests {

    @Mock
    private TextileModelServiceImpl textileMakeService;

    @Mock
    private Model model;

    @InjectMocks
    private TextileModelController textileModelController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetTextileMakes() {
        List<TextileModelDto> textileMakes = new ArrayList<>();
        TextileModelDto textileMakeDto= new TextileModelDto();
        textileMakeDto.setId(1);
        textileMakeDto.setDetails("Detalji 1");
        textileMakeDto.setDescription("Opis 1");
        textileMakes.add(textileMakeDto);
        when(textileMakeService.getTextileModels()).thenReturn(textileMakes);

        String viewName = textileModelController.getTextileTypes(model);
        assertEquals("TextileModel", viewName);
        verify(model, times(1)).addAttribute(eq("textileModels"), eq(textileMakes));
    }

    @Test
    void testAddNew() {
        TextileModelDto textileMakeDto= new TextileModelDto();
        textileMakeDto.setId(1);
        textileMakeDto.setDetails("Detalji 1");
        textileMakeDto.setDescription("Opis 1");
        when(textileMakeService.saveTextileModel(textileMakeDto)).thenReturn(textileMakeDto);

        String viewName = textileModelController.addBew(textileMakeDto);
        assertEquals("redirect:/textileModels", viewName);
        verify(textileMakeService, times(1)).saveTextileModel(textileMakeDto);
    }

    @Test
    void testFindById() {
        int id = 1;
        TextileModelDto textileMakeDto= new TextileModelDto();
        textileMakeDto.setId(1);
        textileMakeDto.setDetails("Detalji 1");
        textileMakeDto.setDescription("Opis 1");
        when(textileMakeService.findById(id)).thenReturn(textileMakeDto);

        TextileModelDto result = textileModelController.findById(id);
        assertEquals(textileMakeDto, result);
        verify(textileMakeService, times(2)).findById(id);
    }

    @Test
    void testUpdate() {
        TextileModelDto textileMakeDto= new TextileModelDto();
        textileMakeDto.setId(1);
        textileMakeDto.setDetails("Detalji 1");
        textileMakeDto.setDescription("Opis 1");
        when(textileMakeService.saveTextileModel(textileMakeDto)).thenReturn(textileMakeDto);

        String viewName = textileModelController.update(textileMakeDto);
        assertEquals("redirect:/textileModels", viewName);
        verify(textileMakeService, times(1)).saveTextileModel(textileMakeDto);
    }

    @Test
    void testDeleteById() {
        int id = 1;

        String viewName = textileModelController.deleteById(id);
        assertEquals("redirect:/textileModels", viewName);
        verify(textileMakeService, times(1)).deleteById(id);
    }
}