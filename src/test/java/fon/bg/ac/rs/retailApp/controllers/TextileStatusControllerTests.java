package fon.bg.ac.rs.retailApp.controllers;

import fon.bg.ac.rs.retailApp.dtos.TextileStatusDto;
import fon.bg.ac.rs.retailApp.servicesImpl.TextileStatusServiceImpl;
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

class TextileStatusControllerTests {

    @Mock
    private TextileStatusServiceImpl textileMakeService;

    @Mock
    private Model model;

    @InjectMocks
    private TextileStatusController textileMakeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetTextileMakes() {
        List<TextileStatusDto> textileMakes = new ArrayList<>();
        TextileStatusDto textileMakeDto= new TextileStatusDto();
        textileMakeDto.setId(1);
        textileMakeDto.setDetails("Detalji 1");
        textileMakeDto.setDescription("Opis 1");
        textileMakes.add(textileMakeDto);
        when(textileMakeService.getTextileStatuses()).thenReturn(textileMakes);

        String viewName = textileMakeController.getTextileStatus(model);
        assertEquals("TextileStatus", viewName);
        verify(model, times(1)).addAttribute(eq("textileStatuses"), eq(textileMakes));
    }

    @Test
    void testAddNew() {
        TextileStatusDto textileMakeDto= new TextileStatusDto();
        textileMakeDto.setId(1);
        textileMakeDto.setDetails("Detalji 1");
        textileMakeDto.setDescription("Opis 1");
        when(textileMakeService.saveTextileStatus(textileMakeDto)).thenReturn(textileMakeDto);

        String viewName = textileMakeController.addBew(textileMakeDto);
        assertEquals("redirect:/textileStatuses", viewName);
        verify(textileMakeService, times(1)).saveTextileStatus(textileMakeDto);
    }

    @Test
    void testFindById() {
        int id = 1;
        TextileStatusDto textileMakeDto= new TextileStatusDto();
        textileMakeDto.setId(1);
        textileMakeDto.setDetails("Detalji 1");
        textileMakeDto.setDescription("Opis 1");
        when(textileMakeService.findById(id)).thenReturn(textileMakeDto);

        TextileStatusDto result = textileMakeController.findById(id);
        assertEquals(textileMakeDto, result);
        verify(textileMakeService, times(2)).findById(id);
    }

}