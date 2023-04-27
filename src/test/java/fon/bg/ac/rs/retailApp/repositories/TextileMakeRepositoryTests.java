package fon.bg.ac.rs.retailApp.repositories;

import fon.bg.ac.rs.retailApp.models.TextileMake;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TextileMakeRepositoryTests {

    @Autowired
    private TextileMakeRepository textileMakeRepository;


    @Test
    void testSave() {
        TextileMake newObj = new TextileMake();
        newObj.setDescription("Opis1");
        newObj.setDetails("Detalji1");
        TextileMake saved = textileMakeRepository.save(newObj);
        assertNotNull(saved.getId());
    }

    @Test
    void testFindById() {
        TextileMake newObj = new TextileMake();
        newObj.setDescription("Opis1");
        newObj.setDetails("Detalji1");
        TextileMake saved = textileMakeRepository.save(newObj);

        TextileMake found = textileMakeRepository.findById(saved.getId()).orElse(null);
        assertNotNull(found);
        assertEquals(saved.getDescription(), found.getDescription());
        assertEquals(saved.getDetails(), found.getDetails());
        //assert other fields are equal as well
    }

    @Test
    void testGetAll() {
        TextileMake newObj1 = new TextileMake();
        newObj1.setDescription("Opis1");
        newObj1.setDetails("Detalji1");
        textileMakeRepository.save(newObj1);

        TextileMake newObj2 = new TextileMake();
        newObj2.setDescription("Opis2");
        newObj2.setDetails("Detalji2");
        textileMakeRepository.save(newObj2);

        List<TextileMake> items = textileMakeRepository.findAll();
        assertEquals(2, items.size());
    }

    @Test
    void testDelete() {
        TextileMake newObj = new TextileMake();
        newObj.setDescription("Opis1");
        newObj.setDetails("Detalji1");
        TextileMake saved = textileMakeRepository.save(newObj);

        textileMakeRepository.deleteById(saved.getId());
        Optional<TextileMake> deleted = textileMakeRepository.findById(saved.getId());
        assertFalse(deleted.isPresent());
    }
}