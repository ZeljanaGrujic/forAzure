package fon.bg.ac.rs.retailApp.repositories;

import fon.bg.ac.rs.retailApp.models.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class LocationRepositoryTests {

    @Autowired
    private LocationRepository locationRepository;


    @Test
    void testSave() {
        Location newObj = new Location();
        newObj.setCity("Grad1");
        newObj.setAddress("Ulica1");
        newObj.setDetails("Broj1");
        Location saved = locationRepository.save(newObj);
        assertNotNull(saved.getId());
    }

    @Test
    void testFindById() {
        Location newObj = new Location();
        newObj.setCity("Grad1");
        newObj.setAddress("Ulica1");
        newObj.setDetails("Broj1");
        Location saved = locationRepository.save(newObj);

        Location found = locationRepository.findById(saved.getId()).orElse(null);
        assertNotNull(found);
        assertEquals(saved.getCity(), found.getCity());
        assertEquals(saved.getAddress(), found.getAddress());
        assertEquals(saved.getDetails(), found.getDetails());
        //assert other fields are equal as well
    }

    @Test
    void testGetAll() {
        Location newObj1 = new Location();
        newObj1.setCity("Grad1");
        newObj1.setAddress("Ulica1");
        newObj1.setDetails("Broj1");
        locationRepository.save(newObj1);

        Location newObj2 = new Location();
        newObj2.setCity("Grad2");
        newObj2.setAddress("Ulica2");
        newObj2.setDetails("Broj2");
        locationRepository.save(newObj2);

        List<Location> items = locationRepository.findAll();
        assertEquals(2, items.size());
    }

    @Test
    void testDelete() {
        Location newObj = new Location();
        newObj.setCity("Grad1");
        newObj.setAddress("Ulica1");
        newObj.setDetails("Broj1");
        Location saved = locationRepository.save(newObj);

        locationRepository.deleteById(saved.getId());
        Optional<Location> deleted = locationRepository.findById(saved.getId());
        assertFalse(deleted.isPresent());
    }
}