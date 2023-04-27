package fon.bg.ac.rs.retailApp.servicesImpl;

import fon.bg.ac.rs.retailApp.dtos.InvoiceBItemDto;
import fon.bg.ac.rs.retailApp.models.InvoiceBItem;
import fon.bg.ac.rs.retailApp.models.InvoiceBuying;
import fon.bg.ac.rs.retailApp.models.Textile;
import fon.bg.ac.rs.retailApp.repositories.InvoiceBItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class InvoiceBItemServiceImplTests {

    @Autowired
    private InvoiceBItemServiceImpl service;
    @MockBean
    private InvoiceBItemRepository repository;

    private final InvoiceBItem newObj = new InvoiceBItem(1, 20,1200, new InvoiceBuying(1,null,null,0,null, 0, "Specijalna naznaka 1" ),1, new Textile(), 0);

    private final InvoiceBItemDto newObjDto = new InvoiceBItemDto(1, 20,1200, new InvoiceBuying(1,null,null,0,null, 0, "Specijalna naznaka 1" ),0, new Textile(), 0);

/*
    @Test
    void saveInvoiceItem() {

        when(repository.save(newObj)).thenReturn(newObj);

        InvoiceBItemDto expected = newObjDto;
        //i sada mogu samo da testiram biznis logiku servisa, i da se fokusiram na to a ne i na rad repozitorijuma
        //apstrahovala sam sve ono sto je ispod jer sam to vec istestirala u repositori testovima
        InvoiceBItemDto found = service.saveInvoiceItem(newObjDto);

        assertNotNull(found);
        assertEquals(expected.getQuantity(), found.getQuantity());
        assertEquals(expected.getTotalCost(), found.getTotalCost());
    }

 */

    @Test
    void findByInvoiceBuyingId() {

        List<InvoiceBItem> items= new ArrayList<>();
        items.add(newObj);
        when(repository.findByInvoiceBuyingId(newObj.getInvoicebuyingid())).thenReturn(items);

        InvoiceBItemDto expected = newObjDto;
        List<InvoiceBItem> found = service.findByInvoiceBuyingId(newObj.getInvoicebuyingid());

        assertNotNull(found);
        assertEquals(expected.getId(), found.get(0).getId());
        assertEquals(expected.getQuantity(), found.get(0).getQuantity());
        assertEquals(expected.getTotalCost(), found.get(0).getTotalCost());
    }

    @Test
    void findById() {
        when(repository.findById(1)).thenReturn(Optional.ofNullable(newObj));

        InvoiceBItemDto expected = newObjDto;
        InvoiceBItemDto found = service.findById(1);

        assertNotNull(found);
        assertEquals(expected.getId(), found.getId());
        assertEquals(expected.getQuantity(), found.getQuantity());
        assertEquals(expected.getTotalCost(), found.getTotalCost());
    }

    @Test
    void deleteById() {
        when(repository.findById(newObj.getId())).thenReturn(Optional.ofNullable(newObj));
        service.deleteById(newObj.getId());
        verify(repository, times(1)).deleteById(newObj.getId());
    }
}