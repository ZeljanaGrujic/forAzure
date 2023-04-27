package fon.bg.ac.rs.retailApp.controllers;

import fon.bg.ac.rs.retailApp.dtos.InvoiceItemDto;
import fon.bg.ac.rs.retailApp.servicesImpl.InvoiceItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class InvoiceItemControllerTests {

    @Mock
    private InvoiceItemServiceImpl invoiceItemService;

    @Mock
    private Model model;

    @InjectMocks
    private InvoiceItemController invoiceItemController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddNew() {
        InvoiceItemDto invoiceItem = new InvoiceItemDto();
        when(invoiceItemService.saveInvoiceItem(invoiceItem)).thenReturn(invoiceItem);

        String viewName = invoiceItemController.addBew(invoiceItem);
        assertEquals("redirect:/invoice/textile/Edit/?id=" + invoiceItem.getInvoicesellingid(), viewName);
        verify(invoiceItemService, times(1)).saveInvoiceItem(invoiceItem);
    }

    @Test
    void testRemoveItem() {
        int id = 1;
        InvoiceItemDto invoiceItem = new InvoiceItemDto();
        invoiceItem.setInvoicesellingid(2);
        when(invoiceItemService.findById(id)).thenReturn(invoiceItem);

        String viewName = invoiceItemController.removeItem(id);
        assertEquals("redirect:/invoice/textile/Edit/?id=" + invoiceItem.getInvoicesellingid(), viewName);
        verify(invoiceItemService, times(1)).findById(id);
        verify(invoiceItemService, times(1)).deleteById(id);
    }

    @Test
    void testDeleteById() {
        int id = 1;
        InvoiceItemDto invoiceItem = new InvoiceItemDto();
        invoiceItem.setInvoicesellingid(2);
        when(invoiceItemService.findById(id)).thenReturn(invoiceItem);

        String viewName = invoiceItemController.deleteById(id);
        assertEquals("redirect:/invoice/textile/Edit/?id=" + invoiceItem.getInvoicesellingid(), viewName);
        verify(invoiceItemService, times(1)).findById(id);
        verify(invoiceItemService, times(1)).deleteById(id);
    }
}