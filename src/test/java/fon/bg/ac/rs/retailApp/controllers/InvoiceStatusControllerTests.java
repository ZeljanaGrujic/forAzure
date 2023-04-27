package fon.bg.ac.rs.retailApp.controllers;

import fon.bg.ac.rs.retailApp.dtos.InvoiceStatusDto;
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

class InvoiceStatusControllerTests {

    @Mock
    private InvoiceStatusServiceImpl invoiceStatusService;

    @Mock
    private Model model;

    @InjectMocks
    private InvoiceStatusController invoiceStatusController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetInvoiceStatus() {
        List<InvoiceStatusDto> invoiceStatuses = new ArrayList<>();
        InvoiceStatusDto invoiceStatusDto= new InvoiceStatusDto();
        invoiceStatusDto.setId(1);
        invoiceStatusDto.setDetails("Detalji 1");
        invoiceStatusDto.setDescription("Opis 1");
        invoiceStatuses.add(invoiceStatusDto);
        when(invoiceStatusService.getInvoiceStatuses()).thenReturn(invoiceStatuses);

        String viewName = invoiceStatusController.getInvoiceStatus(model);
        assertEquals("InvoiceStatus", viewName);
        verify(model, times(1)).addAttribute(eq("invoiceStatuses"), eq(invoiceStatuses));
    }

    @Test
    void testAddNew() {
        InvoiceStatusDto invoiceStatus = new InvoiceStatusDto();
        when(invoiceStatusService.saveInvoiceStatus(invoiceStatus)).thenReturn(invoiceStatus);

        String viewName = invoiceStatusController.addBew(invoiceStatus);
        assertEquals("redirect:/invoiceStatuses", viewName);
        verify(invoiceStatusService, times(1)).saveInvoiceStatus(invoiceStatus);
    }

    @Test
    void testFindById() {
        int id = 1;
        InvoiceStatusDto invoiceStatus = new InvoiceStatusDto();
        when(invoiceStatusService.findById(id)).thenReturn(invoiceStatus);

        InvoiceStatusDto result = invoiceStatusController.findById(id);
        assertEquals(invoiceStatus, result);
        verify(invoiceStatusService, times(2)).findById(id);
    }
}