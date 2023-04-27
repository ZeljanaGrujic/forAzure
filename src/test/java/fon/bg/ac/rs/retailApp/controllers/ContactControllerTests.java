package fon.bg.ac.rs.retailApp.controllers;

import fon.bg.ac.rs.retailApp.dtos.ContactDto;
import fon.bg.ac.rs.retailApp.servicesImpl.ContactServiceImpl;
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

class ContactControllerTests {

    @Mock
    private ContactServiceImpl contactService;

    @Mock
    private Model model;

    @InjectMocks
    private ContactController contactController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetContacts() {
        List<ContactDto> contacts = new ArrayList<>();
        ContactDto contactDto = new ContactDto(1, "Ime 1","Prezime 1", "123456789", "kontakt1@gmail.com", "Detalji1");

        contacts.add(contactDto);
        when(contactService.getContacts()).thenReturn(contacts);

        String viewName = contactController.getContactss(model);
        assertEquals("MyContact", viewName);
        verify(model, times(1)).addAttribute(eq("contacts"), eq(contacts));
    }

    @Test
    void testAddBew() {
        ContactDto contact = new ContactDto();
        when(contactService.saveContact(contact)).thenReturn(contact);

        String viewName = contactController.addBew(contact);
        assertEquals("redirect:/contacts", viewName);
        verify(contactService, times(1)).saveContact(contact);
    }

    @Test
    void testFindById() {
        int id = 1;
        ContactDto contact = new ContactDto();
        when(contactService.findById(id)).thenReturn(contact);

        ContactDto result = contactController.findById(id);
        assertEquals(contact, result);
        verify(contactService, times(2)).findById(id);
    }

    @Test
    void testUpdate() {
        ContactDto contact = new ContactDto();
        when(contactService.saveContact(contact)).thenReturn(contact);

        String viewName = contactController.update(contact);
        assertEquals("redirect:/contacts", viewName);
        verify(contactService, times(1)).saveContact(contact);
    }

    @Test
    void testDeleteById() {
        int id = 1;

        String viewName = contactController.deleteById(id);
        assertEquals("redirect:/contacts", viewName);
        verify(contactService, times(1)).deleteById(id);
    }
}