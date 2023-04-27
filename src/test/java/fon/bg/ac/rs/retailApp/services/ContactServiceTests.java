package fon.bg.ac.rs.retailApp.services;

import fon.bg.ac.rs.retailApp.dtos.ContactDto;
import fon.bg.ac.rs.retailApp.models.Contact;
import fon.bg.ac.rs.retailApp.repositories.ContactRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ContactServiceTests {

    @Autowired
    private ContactService service;
    @MockBean
    private ContactRepository repository;

    private final Contact newObj = new Contact(1, "Ime 1","Prezime 1", "123456789", "kontakt1@gmail.com", "Detalji1");

    private final ContactDto newObjDto = new ContactDto(1, "Ime 1","Prezime 1", "123456789", "kontakt1@gmail.com", "Detalji1");


    @Test
    void getContacts() {
        List<Contact> items = Arrays.asList(newObj);
        when(repository.findAll()).thenReturn(items);

        List<ContactDto> expectedItems = Arrays.asList(newObjDto);
        List<ContactDto> foundItems = service.getContacts();

        assertEquals(expectedItems, foundItems);
    }

    @Test
    void saveContact() {

        //ovim redom simuliram da je entitet uspesno sacuvan u bazi
        when(repository.save(newObj)).thenReturn(newObj);

        ContactDto expected = newObjDto;
        //i sada mogu samo da testiram biznis logiku servisa, i da se fokusiram na to a ne i na rad repozitorijuma
        //apstrahovala sam sve ono sto je ispod jer sam to vec istestirala u repositori testovima
        ContactDto found = service.saveContact(newObjDto);

        assertEquals(expected, found);
    }

    @Test
    void findById() {
        when(repository.findById(1)).thenReturn(Optional.ofNullable(newObj));

        ContactDto expected = newObjDto;
        ContactDto found = service.findById(1);

        assertEquals(expected, found);
    }

    @Test
    void deleteById() {
        when(repository.findById(newObj.getId())).thenReturn(Optional.ofNullable(newObj));
        service.deleteById(newObj.getId());
        verify(repository, times(1)).deleteById(newObj.getId());
    }
}