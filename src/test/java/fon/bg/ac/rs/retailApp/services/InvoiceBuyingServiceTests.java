package fon.bg.ac.rs.retailApp.services;

import fon.bg.ac.rs.retailApp.dtos.InvoiceBuyingDto;
import fon.bg.ac.rs.retailApp.models.InvoiceBuying;
import fon.bg.ac.rs.retailApp.models.InvoiceStatus;
import fon.bg.ac.rs.retailApp.models.Supplier;
import fon.bg.ac.rs.retailApp.repositories.InvoiceBuyingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class InvoiceBuyingServiceTests {

    @Autowired
    private InvoiceBuyingService service;
    @MockBean
    private InvoiceBuyingRepository repository;

    private final InvoiceBuying newObj = new InvoiceBuying(1, null, new InvoiceStatus(), null, new Supplier(),null, "Detalji1");

    private final InvoiceBuyingDto newObjDto = new InvoiceBuyingDto(1, null,new InvoiceStatus(), null, new Supplier(),null, "Detalji1");


    @Test
    void getInvoicesBuying() {
        List<InvoiceBuying> items = Arrays.asList(newObj);
        when(repository.findAll()).thenReturn(items);

        List<InvoiceBuyingDto> expectedItems = Arrays.asList(newObjDto);
        List<InvoiceBuyingDto> foundItems = service.getInvoicesBuying();

        assertEquals(expectedItems, foundItems);
    }

    @Test
    void saveInvoiceBuying() {
        when(repository.save(newObj)).thenReturn(newObj);

        InvoiceBuyingDto expected = newObjDto;
        //i sada mogu samo da testiram biznis logiku servisa, i da se fokusiram na to a ne i na rad repozitorijuma
        //apstrahovala sam sve ono sto je ispod jer sam to vec istestirala u repositori testovima
        InvoiceBuyingDto found = service.saveInvoiceBuying(newObjDto);

        assertEquals(expected, found);
    }

    @Test
    void findById() {
        when(repository.findById(1)).thenReturn(Optional.ofNullable(newObj));

        InvoiceBuyingDto expected = newObjDto;
        InvoiceBuyingDto found = service.findById(1);

        assertEquals(expected, found);
    }
}