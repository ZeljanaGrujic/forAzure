/*
package fon.bg.ac.rs.retailApp.repositories;

import fon.bg.ac.rs.retailApp.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;


    @Test
    void testSave() {
        User newObj = new User();

        newObj.setId(1);
       newObj.setUsername("Korinsicko ime 1");
       newObj.setFirstname("Ime 1");
       newObj.setLastname("Prezime 1");
       newObj.setPassword("Sifra 1");
       newObj.setRoles(null);
        User saved = userRepository.save(newObj);
        assertNotNull(saved.getId());
    }

    @Test
    void testFindById() {
        User newObj = new User();

        newObj.setUsername("Korinsicko ime 1");
        newObj.setFirstname("Ime 1");
        newObj.setLastname("Prezime 1");
        newObj.setPassword("Sifra 1");
        User saved = userRepository.save(newObj);

        User found = userRepository.findById(saved.getId()).orElse(null);
        assertNotNull(found);
        assertEquals(saved.getUsername(), found.getUsername());
        assertEquals(saved.getFirstname(), found.getFirstname());
        assertEquals(saved.getLastname(), found.getLastname());
        assertEquals(saved.getPassword(), found.getPassword());
        //assert other fields are equal as well
    }

    @Test
    void testGetAll() {
        User newObj = new User();

        newObj.setUsername("Korinsicko ime 1");
        newObj.setFirstname("Ime 1");
        newObj.setLastname("Prezime 1");
        newObj.setPassword("Sifra 1");
       userRepository.save(newObj);


        List<User> items = userRepository.findAll();
        assertEquals(1, items.size());
    }

    @Test
    void testDeleteInvoiceBItem() {
        User newObj = new User();

        newObj.setUsername("Korinsicko ime 1");
        newObj.setFirstname("Ime 1");
        newObj.setLastname("Prezime 1");
        newObj.setPassword("Sifra 1");
        User saved = userRepository.save(newObj);

        userRepository.deleteById(saved.getId());
        Optional<User> deleted = userRepository.findById(saved.getId());
        assertFalse(deleted.isPresent());
    }

    @Test
    void testFindByUserName() {
        User newObj = new User();

        newObj.setUsername("Korinsicko ime 1");
        newObj.setFirstname("Ime 1");
        newObj.setLastname("Prezime 1");
        newObj.setPassword("Sifra 1");
        User saved = userRepository.save(newObj);


        User found = userRepository.findByUsername(newObj.getUsername());
        assertNotNull(found);
        assertEquals(saved.getUsername(), found.getUsername());
        assertEquals(saved.getFirstname(), found.getFirstname());
        assertEquals(saved.getLastname(), found.getLastname());
        assertEquals(saved.getPassword(), found.getPassword());
    }

    @Test
    void testFindByFirstnameAndLastname() {
        User newObj = new User();

        newObj.setUsername("Korinsicko ime 1");
        newObj.setFirstname("Ime 1");
        newObj.setLastname("Prezime 1");
        newObj.setPassword("Sifra 1");
        User saved = userRepository.save(newObj);


        User found = userRepository.findByFirstnameAndLastname(newObj.getFirstname(), newObj.getLastname());
        assertNotNull(found);
        assertEquals(saved.getUsername(), found.getUsername());
        assertEquals(saved.getFirstname(), found.getFirstname());
        assertEquals(saved.getLastname(), found.getLastname());
        assertEquals(saved.getPassword(), found.getPassword());
    }

} */