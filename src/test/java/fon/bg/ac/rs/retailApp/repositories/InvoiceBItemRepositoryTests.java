package fon.bg.ac.rs.retailApp.repositories;

import fon.bg.ac.rs.retailApp.models.InvoiceBItem;
import fon.bg.ac.rs.retailApp.models.InvoiceBuying;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class InvoiceBItemRepositoryTests {

    @Autowired
    private InvoiceBItemRepository invoiceBItemRepository;

    @Autowired
    private InvoiceBuyingRepository invoiceBuyingRepository;

    @Test
    void testSaveInvoiceBItem() {
        InvoiceBItem invoiceBItem = new InvoiceBItem();
        invoiceBItem.setQuantity(20);
        invoiceBItem.setTotalCost(12300);
        InvoiceBItem savedInvoiceBItem = invoiceBItemRepository.save(invoiceBItem);
        assertNotNull(savedInvoiceBItem.getId());
    }

    @Test
    void testFindById() {
        InvoiceBItem invoiceBItem = new InvoiceBItem();
        invoiceBItem.setQuantity(20);
        invoiceBItem.setTotalCost(12300);
        InvoiceBItem savedInvoiceBItem = invoiceBItemRepository.save(invoiceBItem);

        InvoiceBItem foundInvoiceBItem = invoiceBItemRepository.findById(savedInvoiceBItem.getId()).orElse(null);
        assertNotNull(foundInvoiceBItem);
        assertEquals(savedInvoiceBItem.getQuantity(), foundInvoiceBItem.getQuantity());
        assertEquals(savedInvoiceBItem.getTotalCost(), foundInvoiceBItem.getTotalCost());
        //assert other fields are equal as well
    }

    @Test
    void testGetAllInvoiceBItems() {
        InvoiceBItem invoiceBItem1 = new InvoiceBItem();
        invoiceBItem1.setQuantity(20);
        invoiceBItem1.setTotalCost(12300);
        invoiceBItemRepository.save(invoiceBItem1);

        InvoiceBItem invoiceBItem2 = new InvoiceBItem();
        invoiceBItem2.setQuantity(30);
        invoiceBItem2.setTotalCost(13600);
        invoiceBItemRepository.save(invoiceBItem2);

        List<InvoiceBItem> invoiceBItems = invoiceBItemRepository.findAll();
        assertEquals(2, invoiceBItems.size());
    }

    @Test
    void testDeleteInvoiceBItem() {
        InvoiceBItem invoiceBItem = new InvoiceBItem();
        invoiceBItem.setQuantity(20);
        invoiceBItem.setTotalCost(12300);
        InvoiceBItem savedInvoiceBItem = invoiceBItemRepository.save(invoiceBItem);

        invoiceBItemRepository.deleteById(savedInvoiceBItem.getId());
        Optional<InvoiceBItem> deletedInvoiceBItem = invoiceBItemRepository.findById(savedInvoiceBItem.getId());
        assertFalse(deletedInvoiceBItem.isPresent());
    }

    @Test
    void testFindByInvoiceBuyingId() {
        InvoiceBuying invoiceBuying = new InvoiceBuying();
        invoiceBuying.setSpecialRemarks("Specijalni detalj 1");
        InvoiceBuying savedInvoiceBuying= invoiceBuyingRepository.save(invoiceBuying);


        InvoiceBItem invoiceBItem = new InvoiceBItem();
        invoiceBItem.setQuantity(20);
        invoiceBItem.setTotalCost(12300);
        invoiceBItem.setInvoiceBuying(savedInvoiceBuying);
        invoiceBItem.setInvoicebuyingid(savedInvoiceBuying.getId());
        invoiceBItemRepository.save(invoiceBItem);


        List<InvoiceBItem> invoiceBItems = invoiceBItemRepository.findByInvoiceBuyingId(savedInvoiceBuying.getId());
        assertEquals(1, invoiceBItems.size());
        assertEquals(invoiceBItem.getId(), invoiceBItems.get(0).getId());
    }

}