package fon.bg.ac.rs.retailApp.controllers;

import fon.bg.ac.rs.retailApp.dtos.InvoiceBuyingDto;
import fon.bg.ac.rs.retailApp.models.InvoiceStatus;
import fon.bg.ac.rs.retailApp.models.Supplier;
import fon.bg.ac.rs.retailApp.servicesImpl.InvoiceBuyingServiceImpl;
import fon.bg.ac.rs.retailApp.servicesImpl.InvoiceStatusServiceImpl;
import fon.bg.ac.rs.retailApp.servicesImpl.SupplierServiceImpl;
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

class InvoiceBuyingControllerTests {

    @Mock
    private SupplierServiceImpl supplierService;

    @Mock
    private InvoiceBuyingServiceImpl invoiceBuyingService;

    @Mock
    private InvoiceStatusServiceImpl invoiceStatusService;

    @Mock
    private Model model;

    @InjectMocks
    private InvoiceBuyingController invoiceBuyingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetInvoicesBuying() {
        List<InvoiceBuyingDto> invoicesBuying = new ArrayList<>();
        InvoiceBuyingDto invoiceBuyingDto = new InvoiceBuyingDto(1, null,new InvoiceStatus(), null, new Supplier(),null, "Detalji1");

        invoicesBuying.add(invoiceBuyingDto);
        when(invoiceBuyingService.getInvoicesBuying()).thenReturn(invoicesBuying);
        when(supplierService.getSuppliers()).thenReturn(new ArrayList<>());
        when(invoiceStatusService.getInvoiceStatuses()).thenReturn(new ArrayList<>());

        String viewName = invoiceBuyingController.getInvoicesBuying(model);
        assertEquals("InvoiceBuying", viewName);
        verify(model, times(1)).addAttribute(eq("invoiceBuyings"), eq(invoicesBuying));
        verify(model, times(1)).addAttribute(eq("suppliers"), any());
        verify(model, times(1)).addAttribute(eq("invoiceStatuses"), any());
    }

    @Test
    void testAddNew() {
        InvoiceBuyingDto invoiceBuying = new InvoiceBuyingDto();
        when(invoiceBuyingService.saveInvoiceBuying(invoiceBuying)).thenReturn(invoiceBuying);

        String viewName = invoiceBuyingController.addBew(invoiceBuying);
        assertEquals("redirect:/invoicesBuying", viewName);
        verify(invoiceBuyingService, times(1)).saveInvoiceBuying(invoiceBuying);
    }

    @Test
    void testFindById() {
        int id = 1;
        InvoiceBuyingDto invoiceBuying = new InvoiceBuyingDto();
        when(invoiceBuyingService.findById(id)).thenReturn(invoiceBuying);

        InvoiceBuyingDto result = invoiceBuyingController.findById(id);
        assertEquals(invoiceBuying, result);
        verify(invoiceBuyingService, times(2)).findById(id);
    }

    @Test
    void testUpdate() {
        InvoiceBuyingDto invoiceBuying = new InvoiceBuyingDto();
        when(invoiceBuyingService.saveInvoiceBuying(invoiceBuying)).thenReturn(invoiceBuying);

        String viewName = invoiceBuyingController.update(invoiceBuying);
        assertEquals("redirect:/invoicesBuying", viewName);
        verify(invoiceBuyingService, times(1)).saveInvoiceBuying(invoiceBuying);
    }

}