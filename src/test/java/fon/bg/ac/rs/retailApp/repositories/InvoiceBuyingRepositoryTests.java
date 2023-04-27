package fon.bg.ac.rs.retailApp.repositories;
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
public class InvoiceBuyingRepositoryTests {

    @Autowired
    private InvoiceBuyingRepository invoiceBuyingRepository;

    @Test
    void testSaveInvoiceBItem() {
        InvoiceBuying invoiceBuying = new InvoiceBuying();
        invoiceBuying.setSpecialRemarks("Specijalna naznaka 1");
        InvoiceBuying savedInvoiceBuying = invoiceBuyingRepository.save(invoiceBuying);
        assertNotNull(savedInvoiceBuying.getId());
    }

    @Test
    void testFindById() {
        InvoiceBuying invoiceBuying = new InvoiceBuying();
        invoiceBuying.setSpecialRemarks("Specijalna naznaka 1");
        InvoiceBuying savedInvoiceBuying = invoiceBuyingRepository.save(invoiceBuying);

        InvoiceBuying foundInvoiceBuying = invoiceBuyingRepository.findById(savedInvoiceBuying.getId()).orElse(null);
        assertNotNull(foundInvoiceBuying);
        assertEquals(savedInvoiceBuying.getSpecialRemarks(), foundInvoiceBuying.getSpecialRemarks());

    }

    @Test
    void testGetAllInvoiceBItems() {
        InvoiceBuying invoiceBuying1 = new InvoiceBuying();
        invoiceBuying1.setSpecialRemarks("Specijalna naznaka 1");
        invoiceBuyingRepository.save(invoiceBuying1);

        InvoiceBuying invoiceBuying2 = new InvoiceBuying();
        invoiceBuying2.setSpecialRemarks("Specijalna naznaka 1");
        invoiceBuyingRepository.save(invoiceBuying2);

        List<InvoiceBuying> invoiceBuyings = invoiceBuyingRepository.findAll();
        assertEquals(2, invoiceBuyings.size());
    }

    @Test
    void testDeleteInvoiceBItem() {
        InvoiceBuying invoiceBuying = new InvoiceBuying();
        invoiceBuying.setSpecialRemarks("Specijalna naznaka 1");
        InvoiceBuying savedInvoiceBuying = invoiceBuyingRepository.save(invoiceBuying);

        invoiceBuyingRepository.deleteById(savedInvoiceBuying.getId());
        Optional<InvoiceBuying> deletedInvoiceBItem = invoiceBuyingRepository.findById(savedInvoiceBuying.getId());
        assertFalse(deletedInvoiceBItem.isPresent());
    }


}