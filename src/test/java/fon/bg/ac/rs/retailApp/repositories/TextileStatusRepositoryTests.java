package fon.bg.ac.rs.retailApp.repositories;

import fon.bg.ac.rs.retailApp.models.TextileStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TextileStatusRepositoryTests {

    @Autowired
    private TextileStatusRepository textileStatusRepository;


    @Test
    void testSave() {
        TextileStatus newObj = new TextileStatus();
        newObj.setDescription("Opis1");
        newObj.setDetails("Detalji1");
        TextileStatus saved = textileStatusRepository.save(newObj);
        assertNotNull(saved.getId());
    }

    @Test
    void testFindById() {
        TextileStatus newObj = new TextileStatus();
        newObj.setDescription("Opis1");
        newObj.setDetails("Detalji1");
        TextileStatus saved = textileStatusRepository.save(newObj);

        TextileStatus found = textileStatusRepository.findById(saved.getId()).orElse(null);
        assertNotNull(found);
        assertEquals(saved.getDescription(), found.getDescription());
        assertEquals(saved.getDetails(), found.getDetails());
        //assert other fields are equal as well
    }

    @Test
    void testGetAll() {
        TextileStatus newObj1 = new TextileStatus();
        newObj1.setDescription("Opis1");
        newObj1.setDetails("Detalji1");
        textileStatusRepository.save(newObj1);

        TextileStatus newObj2 = new TextileStatus();
        newObj2.setDescription("Opis2");
        newObj2.setDetails("Detalji2");
        textileStatusRepository.save(newObj2);

        List<TextileStatus> items = textileStatusRepository.findAll();
        assertEquals(2, items.size());
    }

    @Test
    void testDelete() {
        TextileStatus newObj = new TextileStatus();
        newObj.setDescription("Opis1");
        newObj.setDetails("Detalji1");
        TextileStatus saved = textileStatusRepository.save(newObj);

        textileStatusRepository.deleteById(saved.getId());
        Optional<TextileStatus> deleted = textileStatusRepository.findById(saved.getId());
        assertFalse(deleted.isPresent());
    }
}