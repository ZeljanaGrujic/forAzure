package fon.bg.ac.rs.retailApp.repositories;

import fon.bg.ac.rs.retailApp.models.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ContactRepositoryTests {

    //podesila sam da kad mi se izvrsavaju ovi testovi ne idu direktno do baze nego koriste h2 in memory bazu podataka
    @Autowired
    private ContactRepository contactRepository;

    @Test
    void testSaveContact() {
        Contact contact = new Contact();
        contact.setFirstname("Kontakt1");
        contact.setLastname("Kontakt1");
        contact.setEmail("kontakt1@gmail.com");
        contact.setPhone("123456789");
        contact.setRemarks("Detalji1");
        Contact savedContact = contactRepository.save(contact);
        assertNotNull(savedContact.getId());
    }

    @Test
    void testFindById() {
        Contact contact = new Contact();
        contact.setFirstname("Kontakt1");
        contact.setLastname("Kontakt1");
        contact.setEmail("kontakt1@gmail.com");
        contact.setPhone("123456789");
        contact.setRemarks("Detalji1");
        Contact savedContact = contactRepository.save(contact);

        Contact foundContact = contactRepository.findById(savedContact.getId()).orElse(null);
        assertNotNull(foundContact);
        assertEquals(savedContact.getFirstname(), foundContact.getFirstname());
        assertEquals(savedContact.getLastname(), foundContact.getLastname());
        assertEquals(savedContact.getPhone(), foundContact.getPhone());
        assertEquals(savedContact.getEmail(), foundContact.getEmail());
        assertEquals(savedContact.getRemarks(), foundContact.getRemarks());
    }

    @Test
    void testGetAllContacts() {
        Contact contact1 = new Contact();
        contact1.setFirstname("Kontakt1");
        contact1.setLastname("Kontakt1");
        contact1.setEmail("kontakt1@gmail.com");
        contact1.setPhone("123456789");
        contact1.setRemarks("Detalji1");
        contactRepository.save(contact1);

        Contact contact2 = new Contact();
        contact2.setFirstname("Kontakt2");
        contact2.setLastname("Kontakt2");
        contact2.setEmail("kontakt2@gmail.com");
        contact2.setPhone("123456789");
        contact2.setRemarks("Detalji2");
        contactRepository.save(contact2);

        List<Contact> contacts = contactRepository.findAll();
        assertEquals(2, contacts.size());
    }


    @Test
    void testDeleteContact() {
        Contact contact = new Contact();
        contact.setFirstname("Kontakt1");
        contact.setLastname("Kontakt1");
        contact.setEmail("kontakt1@gmail.com");
        contact.setPhone("123456789");
        contact.setRemarks("Detalji1");
        Contact savedContact = contactRepository.save(contact);

        contactRepository.deleteById(savedContact.getId());
        Optional<Contact> deletedContact=contactRepository.findById(savedContact.getId());
        assertFalse(deletedContact.isPresent());
    }
}