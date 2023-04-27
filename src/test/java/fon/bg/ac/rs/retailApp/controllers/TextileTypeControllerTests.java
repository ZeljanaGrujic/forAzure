package fon.bg.ac.rs.retailApp.controllers;

import fon.bg.ac.rs.retailApp.dtos.TextileTypeDto;
import fon.bg.ac.rs.retailApp.servicesImpl.TextileTypeServiceImpl;
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

class TextileTypeControllerTests {

    @Mock
    private TextileTypeServiceImpl textileMakeService;

    @Mock
    private Model model;

    @InjectMocks
    private TextileTypeController textileModelController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetTextileMakes() {
        List<TextileTypeDto> textileMakes = new ArrayList<>();
        TextileTypeDto textileMakeDto= new TextileTypeDto();
        textileMakeDto.setId(1);
        textileMakeDto.setDetails("Detalji 1");
        textileMakeDto.setDescription("Opis 1");
        textileMakes.add(textileMakeDto);
        when(textileMakeService.getTextileTypes()).thenReturn(textileMakes);

        String viewName = textileModelController.getTextileTypes(model);
        assertEquals("TextileType", viewName);
        verify(model, times(1)).addAttribute(eq("textileTypes"), eq(textileMakes));
    }

    @Test
    void testAddNew() {
        TextileTypeDto textileMakeDto= new TextileTypeDto();
        textileMakeDto.setId(1);
        textileMakeDto.setDetails("Detalji 1");
        textileMakeDto.setDescription("Opis 1");
        when(textileMakeService.saveTextileType(textileMakeDto)).thenReturn(textileMakeDto);

        String viewName = textileModelController.addBew(textileMakeDto);
        assertEquals("redirect:/textileTypes", viewName);
        verify(textileMakeService, times(1)).saveTextileType(textileMakeDto);
    }

    @Test
    void testFindById() {
        int id = 1;
        TextileTypeDto textileMakeDto= new TextileTypeDto();
        textileMakeDto.setId(1);
        textileMakeDto.setDetails("Detalji 1");
        textileMakeDto.setDescription("Opis 1");
        when(textileMakeService.findById(id)).thenReturn(textileMakeDto);

        TextileTypeDto result = textileModelController.findById(id);
        assertEquals(textileMakeDto, result);
        verify(textileMakeService, times(2)).findById(id);
    }

    @Test
    void testUpdate() {
        TextileTypeDto textileMakeDto= new TextileTypeDto();
        textileMakeDto.setId(1);
        textileMakeDto.setDetails("Detalji 1");
        textileMakeDto.setDescription("Opis 1");
        when(textileMakeService.saveTextileType(textileMakeDto)).thenReturn(textileMakeDto);

        String viewName = textileModelController.update(textileMakeDto);
        assertEquals("redirect:/textileTypes", viewName);
        verify(textileMakeService, times(1)).saveTextileType(textileMakeDto);
    }

    @Test
    void testDeleteById() {
        int id = 1;

        String viewName = textileModelController.deleteById(id);
        assertEquals("redirect:/textileTypes", viewName);
        verify(textileMakeService, times(1)).deleteById(id);
    }
}