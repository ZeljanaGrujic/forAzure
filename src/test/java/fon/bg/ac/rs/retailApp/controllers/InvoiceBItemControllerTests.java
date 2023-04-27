package fon.bg.ac.rs.retailApp.controllers;

import fon.bg.ac.rs.retailApp.dtos.InvoiceBItemDto;
import fon.bg.ac.rs.retailApp.servicesImpl.InvoiceBItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class InvoiceBItemControllerTests {

    @Mock
    private InvoiceBItemServiceImpl invoiceBItemService;

    @Mock
    private Model model;

    @InjectMocks
    private InvoiceBItemController invoiceBItemController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddNew() {
        InvoiceBItemDto invoiceBItem = new InvoiceBItemDto();
        when(invoiceBItemService.saveInvoiceItem(invoiceBItem)).thenReturn(invoiceBItem);

        String viewName = invoiceBItemController.addBew(invoiceBItem);
        assertEquals("redirect:/invoiceb/textile/Edit/?id=" + invoiceBItem.getInvoicebuyingid(), viewName);
        verify(invoiceBItemService, times(1)).saveInvoiceItem(invoiceBItem);
    }

    @Test
    void testRemoveItem() {
        int id = 1;
        InvoiceBItemDto invoiceBItem = new InvoiceBItemDto();
        invoiceBItem.setInvoicebuyingid(2);
        when(invoiceBItemService.findById(id)).thenReturn(invoiceBItem);

        String viewName = invoiceBItemController.removeItem(id);
        assertEquals("redirect:/invoiceb/textile/Edit/?id=" + invoiceBItem.getInvoicebuyingid(), viewName);
        verify(invoiceBItemService, times(1)).findById(id);
        verify(invoiceBItemService, times(1)).deleteById(id);
    }

    @Test
    void testDeleteById() {
        int id = 1;
        InvoiceBItemDto invoiceBItem = new InvoiceBItemDto();
        invoiceBItem.setInvoicebuyingid(2);
        when(invoiceBItemService.findById(id)).thenReturn(invoiceBItem);

        String viewName = invoiceBItemController.deleteById(id);
        assertEquals("redirect:/invoiceb/textile/Edit/?id=" + invoiceBItem.getInvoicebuyingid(), viewName);
        verify(invoiceBItemService, times(1)).findById(id);
        verify(invoiceBItemService, times(1)).deleteById(id);
    }
}