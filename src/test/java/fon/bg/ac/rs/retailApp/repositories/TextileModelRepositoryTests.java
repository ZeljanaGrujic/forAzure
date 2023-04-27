package fon.bg.ac.rs.retailApp.repositories;

import fon.bg.ac.rs.retailApp.models.TextileModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TextileModelRepositoryTests {

    @Autowired
    private TextileModelRepository textileModelRepository;


    @Test
    void testSave() {
        TextileModel newObj = new TextileModel();
        newObj.setDescription("Opis1");
        newObj.setDetails("Detalji1");
        TextileModel saved = textileModelRepository.save(newObj);
        assertNotNull(saved.getId());
    }

    @Test
    void testFindById() {
        TextileModel newObj = new TextileModel();
        newObj.setDescription("Opis1");
        newObj.setDetails("Detalji1");
        TextileModel saved = textileModelRepository.save(newObj);

        TextileModel found = textileModelRepository.findById(saved.getId()).orElse(null);
        assertNotNull(found);
        assertEquals(saved.getDescription(), found.getDescription());
        assertEquals(saved.getDetails(), found.getDetails());
        //assert other fields are equal as well
    }

    @Test
    void testGetAll() {
        TextileModel newObj1 = new TextileModel();
        newObj1.setDescription("Opis1");
        newObj1.setDetails("Detalji1");
        textileModelRepository.save(newObj1);

        TextileModel newObj2 = new TextileModel();
        newObj2.setDescription("Opis2");
        newObj2.setDetails("Detalji2");
        textileModelRepository.save(newObj2);

        List<TextileModel> items = textileModelRepository.findAll();
        assertEquals(2, items.size());
    }

    @Test
    void testDelete() {
        TextileModel newObj = new TextileModel();
        newObj.setDescription("Opis1");
        newObj.setDetails("Detalji1");
        TextileModel saved = textileModelRepository.save(newObj);

        textileModelRepository.deleteById(saved.getId());
        Optional<TextileModel> deleted = textileModelRepository.findById(saved.getId());
        assertFalse(deleted.isPresent());
    }
}