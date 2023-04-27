package fon.bg.ac.rs.retailApp.servicesImpl;

import fon.bg.ac.rs.retailApp.dtos.InvoiceSellingDto;
import fon.bg.ac.rs.retailApp.models.Client;
import fon.bg.ac.rs.retailApp.models.InvoiceSelling;
import fon.bg.ac.rs.retailApp.models.InvoiceStatus;
import fon.bg.ac.rs.retailApp.repositories.InvoiceSellingRepository;
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
class InvoiceSellingServiceImplTests {

    @Autowired
    private InvoiceSellingServiceImpl service;
    @MockBean
    private InvoiceSellingRepository repository;

    private final InvoiceSelling newObj = new InvoiceSelling(1, null, new InvoiceStatus(), null, new Client(),0, "Detalji1");

    private final InvoiceSellingDto newObjDto = new InvoiceSellingDto(1, null,new InvoiceStatus(), null, new Client(),0, "Detalji1");


    @Test
    void getInvoicesBuying() {
        List<InvoiceSelling> items = Arrays.asList(newObj);
        when(repository.findAll()).thenReturn(items);

        List<InvoiceSellingDto> expectedItems = Arrays.asList(newObjDto);
        List<InvoiceSellingDto> foundItems = service.getInvoicesSelling();

        assertEquals(expectedItems, foundItems);
    }

    @Test
    void saveInvoiceBuying() {
        when(repository.save(newObj)).thenReturn(newObj);

        InvoiceSellingDto expected = newObjDto;
        //i sada mogu samo da testiram biznis logiku servisa, i da se fokusiram na to a ne i na rad repozitorijuma
        //apstrahovala sam sve ono sto je ispod jer sam to vec istestirala u repositori testovima
        InvoiceSellingDto found = service.saveInvoiceSelling(newObjDto);

        assertEquals(expected, found);
    }

    @Test
    void findById() {
        when(repository.findById(1)).thenReturn(Optional.ofNullable(newObj));

        InvoiceSellingDto expected = newObjDto;
        InvoiceSellingDto found = service.findById(1);

        assertEquals(expected, found);
    }
}