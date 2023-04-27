package fon.bg.ac.rs.retailApp.repositories;

import fon.bg.ac.rs.retailApp.models.InvoiceItem;
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
public class InvoiceItemRepositoryTests {

    @Autowired
    private InvoiceItemRepository invoiceItemRepository;

    @Autowired
    private InvoiceSellingRepository invoiceSellingRepository;

    @Test
    void testSaveInvoiceItem() {
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setQuantity(20);
        invoiceItem.setTotalCost(12300);
        InvoiceItem savedInvoiceItem = invoiceItemRepository.save(invoiceItem);
        assertNotNull(savedInvoiceItem.getId());
    }

    @Test
    void testFindById() {
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setQuantity(20);
        invoiceItem.setTotalCost(12300);
        InvoiceItem savedInvoiceItem = invoiceItemRepository.save(invoiceItem);

        InvoiceItem foundInvoiceItem = invoiceItemRepository.findById(savedInvoiceItem.getId()).orElse(null);
        assertNotNull(foundInvoiceItem);
        assertEquals(savedInvoiceItem.getQuantity(), foundInvoiceItem.getQuantity());
        assertEquals(savedInvoiceItem.getTotalCost(), foundInvoiceItem.getTotalCost());
        //assert other fields are equal as well
    }

    @Test
    void testGetAllInvoiceBItems() {
        InvoiceItem invoiceItem1 = new InvoiceItem();
        invoiceItem1.setQuantity(20);
        invoiceItem1.setTotalCost(12300);
        invoiceItemRepository.save(invoiceItem1);

        InvoiceItem invoiceItem2 = new InvoiceItem();
        invoiceItem2.setQuantity(30);
        invoiceItem2.setTotalCost(13600);
        invoiceItemRepository.save(invoiceItem2);

        List<InvoiceItem> invoiceBItems = invoiceItemRepository.findAll();
        assertEquals(2, invoiceBItems.size());
    }

    @Test
    void testDeleteInvoiceBItem() {
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setQuantity(20);
        invoiceItem.setTotalCost(12300);
        InvoiceItem savedInvoiceBItem = invoiceItemRepository.save(invoiceItem);

        invoiceItemRepository.deleteById(savedInvoiceBItem.getId());
        Optional<InvoiceItem> deletedInvoiceBItem = invoiceItemRepository.findById(savedInvoiceBItem.getId());
        assertFalse(deletedInvoiceBItem.isPresent());
    }

    @Test
    void testFindByInvoiceBuyingId() {
        InvoiceSelling invoiceSelling = new InvoiceSelling();
        invoiceSelling.setSpecialRemarks("Specijalni detalj 1");
        InvoiceSelling savedInvoiceSelling= invoiceSellingRepository.save(invoiceSelling);


        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setQuantity(20);
        invoiceItem.setTotalCost(12300);
        invoiceItem.setInvoiceSelling(savedInvoiceSelling);
        invoiceItem.setInvoicesellingid(savedInvoiceSelling.getId());
        invoiceItemRepository.save(invoiceItem);


        List<InvoiceItem> invoiceItems = invoiceItemRepository.findByInvoiceSellingId(savedInvoiceSelling.getId());
        assertEquals(1, invoiceItems.size());
        assertEquals(invoiceItem.getId(), invoiceItems.get(0).getId());
    }

}