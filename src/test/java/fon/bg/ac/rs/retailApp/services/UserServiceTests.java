package fon.bg.ac.rs.retailApp.services;

import fon.bg.ac.rs.retailApp.models.User;
import fon.bg.ac.rs.retailApp.repositories.UserRepository;
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
class UserServiceTests {

    @Autowired
    private UserService service;
    @MockBean
    private UserRepository repository;

    @Test
    void saveUser() {

        User newObj = new User();
        newObj.setFirstname("Korisnik 1");
        newObj.setLastname("Korisnik 1");
        newObj.setUsername("Korisnicko ime 1");
        newObj.setPassword("Lozinka 1");
        newObj.setId(1);

        when(repository.save(newObj)).thenReturn(newObj);


        //i sada mogu samo da testiram biznis logiku servisa, i da se fokusiram na to a ne i na rad repozitorijuma
        //apstrahovala sam sve ono sto je ispod jer sam to vec istestirala u repositori testovima
        User found = service.saveUser(newObj);

        assertEquals(newObj, found);
    }

    @Test
    void findByUsername() {

        User newObj = new User();
        newObj.setFirstname("Korisnik 1");
        newObj.setLastname("Korisnik 1");
        newObj.setUsername("Korisnicko ime 1");
        newObj.setPassword("Lozinka 1");
        newObj.setId(1);

        when(repository.findByUsername(newObj.getUsername())).thenReturn(newObj);


        User found = service.findByUsername(newObj.getUsername());

        assertNotNull(found);
        assertEquals(newObj, found);


    }

    @Test
    void findByFirstnameAndLastname() {

        User newObj = new User();
        newObj.setFirstname("Korisnik 1");
        newObj.setLastname("Korisnik 1");
        newObj.setUsername("Korisnicko ime 1");
        newObj.setPassword("Lozinka 1");
        newObj.setId(1);

        when(repository.findByFirstnameAndLastname(newObj.getFirstname(), newObj.getLastname())).thenReturn(newObj);


        User found = service.findByFirstnameAndLastname(newObj.getFirstname(), newObj.getLastname());

        assertNotNull(found);
        assertEquals(newObj, found);
    }

    @Test
    void findById() {

        User newObj = new User();
        newObj.setFirstname("Korisnik 1");
        newObj.setLastname("Korisnik 1");
        newObj.setUsername("Korisnicko ime 1");
        newObj.setPassword("Lozinka 1");
        newObj.setId(1);

        when(repository.findById(1)).thenReturn(Optional.ofNullable(newObj));


        User found = service.findById(1).orElse(null);

        assertNotNull(found);
        assertEquals(newObj, found);
    }

    @Test
    void deleteById() {

        User newObj = new User();
        newObj.setFirstname("Korisnik 1");
        newObj.setLastname("Korisnik 1");
        newObj.setUsername("Korisnicko ime 1");
        newObj.setPassword("Lozinka 1");
        newObj.setId(1);
        when(repository.findById(newObj.getId())).thenReturn(Optional.ofNullable(newObj));
        service.deleteById(newObj.getId());
        verify(repository, times(1)).deleteById(newObj.getId());
    }

    @Test
    void getUsers() {

        User newObj = new User();
        newObj.setFirstname("Korisnik 1");
        newObj.setLastname("Korisnik 1");
        newObj.setUsername("Korisnicko ime 1");
        newObj.setPassword("Lozinka 1");
        newObj.setId(1);

        List<User> items= new ArrayList<>();
        items.add(newObj);
        when(repository.findAll()).thenReturn(items);

        List<User> foundItems = service.getUsers();
        assertEquals(items, foundItems);
    }
}