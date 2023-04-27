package fon.bg.ac.rs.retailApp.services;

import fon.bg.ac.rs.retailApp.dtos.ContactDto;
import fon.bg.ac.rs.retailApp.models.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    List<ContactDto> getContacts();

    ContactDto saveContact(ContactDto contact);

    ContactDto findById(int id);

    void deleteById(int id);
}
