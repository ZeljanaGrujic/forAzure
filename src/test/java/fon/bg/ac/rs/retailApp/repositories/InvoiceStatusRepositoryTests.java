package fon.bg.ac.rs.retailApp.repositories;

import fon.bg.ac.rs.retailApp.models.InvoiceStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class InvoiceStatusRepositoryTests {

    @Autowired
    private InvoiceStatusRepository invoiceStatusRepository;


    @Test
    void testSaveInvoiceStatus() {
        InvoiceStatus invoiceStatus = new InvoiceStatus();
        invoiceStatus.setDescription("Opis1");
        invoiceStatus.setDetails("Detalji1");
        InvoiceStatus savedInvoiceStatus = invoiceStatusRepository.save(invoiceStatus);
        assertNotNull(savedInvoiceStatus.getId());
    }

    @Test
    void testFindById() {
        InvoiceStatus invoiceStatus = new InvoiceStatus();
        invoiceStatus.setDescription("Opis1");
        invoiceStatus.setDetails("Detalji1");
        InvoiceStatus savedInvoiceStatus = invoiceStatusRepository.save(invoiceStatus);

        InvoiceStatus foundInvoiceStatus = invoiceStatusRepository.findById(savedInvoiceStatus.getId()).orElse(null);
        assertNotNull(foundInvoiceStatus);
        assertEquals(savedInvoiceStatus.getDescription(), foundInvoiceStatus.getDescription());
        assertEquals(savedInvoiceStatus.getDetails(), foundInvoiceStatus.getDetails());
        //assert other fields are equal as well
    }

    @Test
    void testGetAllInvoiceStatuses() {
        InvoiceStatus invoiceStatus1 = new InvoiceStatus();
        invoiceStatus1.setDescription("Opis1");
        invoiceStatus1.setDetails("Detalji1");
        invoiceStatusRepository.save(invoiceStatus1);

        InvoiceStatus invoiceStatus2 = new InvoiceStatus();
        invoiceStatus2.setDescription("Opis2");
        invoiceStatus2.setDetails("Detalji2");
        invoiceStatusRepository.save(invoiceStatus2);

        List<InvoiceStatus> invoiceStatuses = invoiceStatusRepository.findAll();
        assertEquals(2, invoiceStatuses.size());
    }

    @Test
    void testDeleteInvoiceBItem() {
        InvoiceStatus invoiceStatus = new InvoiceStatus();
        invoiceStatus.setDescription("Opis1");
        invoiceStatus.setDetails("Detalji1");
        InvoiceStatus savedInvoiceStatus = invoiceStatusRepository.save(invoiceStatus);

        invoiceStatusRepository.deleteById(savedInvoiceStatus.getId());
        Optional<InvoiceStatus> deletedInvoiceStatus = invoiceStatusRepository.findById(savedInvoiceStatus.getId());
        assertFalse(deletedInvoiceStatus.isPresent());
    }
}