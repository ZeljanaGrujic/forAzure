package fon.bg.ac.rs.retailApp.servicesImpl;

import fon.bg.ac.rs.retailApp.dtos.InvoiceStatusDto;
import fon.bg.ac.rs.retailApp.models.InvoiceStatus;
import fon.bg.ac.rs.retailApp.repositories.InvoiceStatusRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class InvoiceStatusServiceImplTests {


    @Autowired
    private InvoiceStatusServiceImpl service;
    @MockBean
    private InvoiceStatusRepository repository;

    @Test
    void getInvoiceStatuses() {
        InvoiceStatus newObj= new InvoiceStatus();
        newObj.setId(1);
        newObj.setDetails("Detalji 1");
        newObj.setDescription("Opis 1");

        InvoiceStatusDto newObjDto= new InvoiceStatusDto();
        newObjDto.setId(1);
        newObjDto.setDetails("Detalji 1");
        newObjDto.setDescription("Opis 1");

        List<InvoiceStatus> items = new ArrayList<>();
        items.add(newObj);
        when(repository.findAll()).thenReturn(items);

        List<InvoiceStatusDto> expectedItems = Arrays.asList(newObjDto);
        List<InvoiceStatusDto> foundItems = service.getInvoiceStatuses();

        assertEquals(expectedItems, foundItems);
    }

    @Test
    void saveInvoiceStatus() {
        InvoiceStatus newObj= new InvoiceStatus();
        newObj.setId(1);
        newObj.setDetails("Detalji 1");
        newObj.setDescription("Opis 1");

        InvoiceStatusDto newObjDto= new InvoiceStatusDto();
        newObjDto.setId(1);
        newObjDto.setDetails("Detalji 1");
        newObjDto.setDescription("Opis 1");

        when(repository.save(newObj)).thenReturn(newObj);

        InvoiceStatusDto expected = newObjDto;
        //i sada mogu samo da testiram biznis logiku servisa, i da se fokusiram na to a ne i na rad repozitorijuma
        //apstrahovala sam sve ono sto je ispod jer sam to vec istestirala u repositori testovima
        InvoiceStatusDto found = service.saveInvoiceStatus(newObjDto);

        assertEquals(expected, found);
    }

    @Test
    void findById() {

        InvoiceStatus newObj= new InvoiceStatus();
        newObj.setId(1);
        newObj.setDetails("Detalji 1");
        newObj.setDescription("Opis 1");

        InvoiceStatusDto newObjDto= new InvoiceStatusDto();
        newObjDto.setId(1);
        newObjDto.setDetails("Detalji 1");
        newObjDto.setDescription("Opis 1");

        when(repository.findById(1)).thenReturn(Optional.ofNullable(newObj));

        InvoiceStatusDto expected = newObjDto;
        InvoiceStatusDto found = service.findById(1);

        assertEquals(expected, found);
    }
}