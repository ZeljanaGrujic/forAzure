package fon.bg.ac.rs.retailApp.repositories;

import fon.bg.ac.rs.retailApp.models.InvoiceSelling;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class InvoiceSellingRepositoryTests {

    @Autowired
    private InvoiceSellingRepository invoiceSellingRepository;

    @Test
    void testSaveInvoiceSelling() {
        InvoiceSelling invoiceSelling = new InvoiceSelling();
        invoiceSelling.setSpecialRemarks("Specijalna naznaka 1");
        InvoiceSelling savedInvoiceSelling = invoiceSellingRepository.save(invoiceSelling);
        assertNotNull(savedInvoiceSelling.getId());
    }

    @Test
    void testFindById() {
        InvoiceSelling invoiceSelling = new InvoiceSelling();
        invoiceSelling.setSpecialRemarks("Specijalna naznaka 1");
        InvoiceSelling savedInvoiceSelling = invoiceSellingRepository.save(invoiceSelling);

        InvoiceSelling foundInvoiceSelling = invoiceSellingRepository.findById(savedInvoiceSelling.getId()).orElse(null);
        assertNotNull(foundInvoiceSelling);
        assertEquals(savedInvoiceSelling.getSpecialRemarks(), foundInvoiceSelling.getSpecialRemarks());

    }

    @Test
    void testGetAllInvoiceSellings() {
        InvoiceSelling invoiceSelling1 = new InvoiceSelling();
        invoiceSelling1.setSpecialRemarks("Specijalna naznaka 1");
        invoiceSellingRepository.save(invoiceSelling1);

        InvoiceSelling invoiceSelling2 = new InvoiceSelling();
        invoiceSelling2.setSpecialRemarks("Specijalna naznaka 1");
        invoiceSellingRepository.save(invoiceSelling2);

        List<InvoiceSelling> invoiceSellings = invoiceSellingRepository.findAll();
        assertEquals(2, invoiceSellings.size());
    }

    @Test
    void testDeleteInvoiceSelling() {
        InvoiceSelling invoiceSelling = new InvoiceSelling();
        invoiceSelling.setSpecialRemarks("Specijalna naznaka 1");
        InvoiceSelling savedInvoiceSelling = invoiceSellingRepository.save(invoiceSelling);

        invoiceSellingRepository.deleteById(savedInvoiceSelling.getId());
        Optional<InvoiceSelling> deletedInvoiceSelling = invoiceSellingRepository.findById(savedInvoiceSelling.getId());
        assertFalse(deletedInvoiceSelling.isPresent());
    }


}