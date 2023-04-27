package fon.bg.ac.rs.retailApp.repositories;

import fon.bg.ac.rs.retailApp.models.TextileType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TextileTypeRepositoryTests {

    @Autowired
    private TextileTypeRepository textileTypeRepository;


    @Test
    void testSave() {
        TextileType newObj = new TextileType();
        newObj.setDescription("Opis1");
        newObj.setDetails("Detalji1");
        TextileType saved = textileTypeRepository.save(newObj);
        assertNotNull(saved.getId());
    }

    @Test
    void testFindById() {
        TextileType newObj = new TextileType();
        newObj.setDescription("Opis1");
        newObj.setDetails("Detalji1");
        TextileType saved = textileTypeRepository.save(newObj);

        TextileType found = textileTypeRepository.findById(saved.getId()).orElse(null);
        assertNotNull(found);
        assertEquals(saved.getDescription(), found.getDescription());
        assertEquals(saved.getDetails(), found.getDetails());
        //assert other fields are equal as well
    }

    @Test
    void testGetAll() {
        TextileType newObj1 = new TextileType();
        newObj1.setDescription("Opis1");
        newObj1.setDetails("Detalji1");
        textileTypeRepository.save(newObj1);

        TextileType newObj2 = new TextileType();
        newObj2.setDescription("Opis2");
        newObj2.setDetails("Detalji2");
        textileTypeRepository.save(newObj2);

        List<TextileType> items = textileTypeRepository.findAll();
        assertEquals(2, items.size());
    }

    @Test
    void testDelete() {
        TextileType newObj = new TextileType();
        newObj.setDescription("Opis1");
        newObj.setDetails("Detalji1");
        TextileType saved = textileTypeRepository.save(newObj);

        textileTypeRepository.deleteById(saved.getId());
        Optional<TextileType> deleted = textileTypeRepository.findById(saved.getId());
        assertFalse(deleted.isPresent());
    }
}