package fon.bg.ac.rs.retailApp.controllers;

import fon.bg.ac.rs.retailApp.dtos.TextileMakeDto;
import fon.bg.ac.rs.retailApp.servicesImpl.TextileMakeServiceImpl;
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

class TextileMakeControllerTests {

    @Mock
    private TextileMakeServiceImpl textileMakeService;

    @Mock
    private Model model;

    @InjectMocks
    private TextileMakeController textileMakeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetTextileMakes() {
        List<TextileMakeDto> textileMakes = new ArrayList<>();
        TextileMakeDto textileMakeDto= new TextileMakeDto();
        textileMakeDto.setId(1);
        textileMakeDto.setDetails("Detalji 1");
        textileMakeDto.setDescription("Opis 1");
        textileMakes.add(textileMakeDto);
        when(textileMakeService.getTextileMakes()).thenReturn(textileMakes);

        String viewName = textileMakeController.getTextileMakes(model);
        assertEquals("TextileMake", viewName);
        verify(model, times(1)).addAttribute(eq("textileMakes"), eq(textileMakes));
    }

    @Test
    void testAddNew() {
        TextileMakeDto textileMakeDto= new TextileMakeDto();
        textileMakeDto.setId(1);
        textileMakeDto.setDetails("Detalji 1");
        textileMakeDto.setDescription("Opis 1");
        when(textileMakeService.saveTextileMake(textileMakeDto)).thenReturn(textileMakeDto);

        String viewName = textileMakeController.addBew(textileMakeDto);
        assertEquals("redirect:/textileMakes", viewName);
        verify(textileMakeService, times(1)).saveTextileMake(textileMakeDto);
    }

    @Test
    void testFindById() {
        int id = 1;
        TextileMakeDto textileMakeDto= new TextileMakeDto();
        textileMakeDto.setId(1);
        textileMakeDto.setDetails("Detalji 1");
        textileMakeDto.setDescription("Opis 1");
        when(textileMakeService.findById(id)).thenReturn(textileMakeDto);

        TextileMakeDto result = textileMakeController.findById(id);
        assertEquals(textileMakeDto, result);
        verify(textileMakeService, times(2)).findById(id);
    }

    @Test
    void testUpdate() {
        TextileMakeDto textileMakeDto= new TextileMakeDto();
        textileMakeDto.setId(1);
        textileMakeDto.setDetails("Detalji 1");
        textileMakeDto.setDescription("Opis 1");
        when(textileMakeService.saveTextileMake(textileMakeDto)).thenReturn(textileMakeDto);

        String viewName = textileMakeController.update(textileMakeDto);
        assertEquals("redirect:/textileMakes", viewName);
        verify(textileMakeService, times(1)).saveTextileMake(textileMakeDto);
    }

    @Test
    void testDeleteById() {
        int id = 1;

        String viewName = textileMakeController.deleteById(id);
        assertEquals("redirect:/textileMakes", viewName);
        verify(textileMakeService, times(1)).deleteById(id);
    }
}