package fon.bg.ac.rs.retailApp.controllers;

import fon.bg.ac.rs.retailApp.dtos.InvoiceSellingDto;
import fon.bg.ac.rs.retailApp.models.Client;
import fon.bg.ac.rs.retailApp.models.InvoiceStatus;
import fon.bg.ac.rs.retailApp.servicesImpl.ClientServiceImpl;
import fon.bg.ac.rs.retailApp.servicesImpl.InvoiceSellingServiceImpl;
import fon.bg.ac.rs.retailApp.servicesImpl.InvoiceStatusServiceImpl;
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

class InvoiceSellingControllerTests {

    @Mock
    private ClientServiceImpl supplierService;

    @Mock
    private InvoiceSellingServiceImpl invoiceBuyingService;

    @Mock
    private InvoiceStatusServiceImpl invoiceStatusService;

    @Mock
    private Model model;

    @InjectMocks
    private InvoiceSellingController invoiceBuyingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetInvoicesSelling() {
        List<InvoiceSellingDto> invoicesBuying = new ArrayList<>();
        InvoiceSellingDto invoiceBuyingDto = new InvoiceSellingDto(1, null,new InvoiceStatus(), null, new Client(),null, "Detalji1");

        invoicesBuying.add(invoiceBuyingDto);
        when(invoiceBuyingService.getInvoicesSelling()).thenReturn(invoicesBuying);
        when(supplierService.getClients()).thenReturn(new ArrayList<>());
        when(invoiceStatusService.getInvoiceStatuses()).thenReturn(new ArrayList<>());

        String viewName = invoiceBuyingController.getInvoicesSelling(model);
        assertEquals("InvoiceSelling", viewName);
        verify(model, times(1)).addAttribute(eq("invoiceSellings"), eq(invoicesBuying));
        verify(model, times(1)).addAttribute(eq("clients"), any());
        verify(model, times(1)).addAttribute(eq("invoiceStatuses"), any());
    }

    @Test
    void testAddNew() {
        InvoiceSellingDto invoiceBuying = new InvoiceSellingDto();
        when(invoiceBuyingService.saveInvoiceSelling(invoiceBuying)).thenReturn(invoiceBuying);

        String viewName = invoiceBuyingController.addBew(invoiceBuying);
        assertEquals("redirect:/invoicesSelling", viewName);
        verify(invoiceBuyingService, times(1)).saveInvoiceSelling(invoiceBuying);
    }

    @Test
    void testFindById() {
        int id = 1;
        InvoiceSellingDto invoiceBuying = new InvoiceSellingDto();
        when(invoiceBuyingService.findById(id)).thenReturn(invoiceBuying);

        InvoiceSellingDto result = invoiceBuyingController.findById(id);
        assertEquals(invoiceBuying, result);
        verify(invoiceBuyingService, times(2)).findById(id);
    }

    @Test
    void testUpdate() {
        InvoiceSellingDto invoiceBuying = new InvoiceSellingDto();
        when(invoiceBuyingService.saveInvoiceSelling(invoiceBuying)).thenReturn(invoiceBuying);

        String viewName = invoiceBuyingController.update(invoiceBuying);
        assertEquals("redirect:/invoicesSelling", viewName);
        verify(invoiceBuyingService, times(1)).saveInvoiceSelling(invoiceBuying);
    }

}