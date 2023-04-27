package fon.bg.ac.rs.retailApp.repositories;

import fon.bg.ac.rs.retailApp.models.Textile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TextileRepositoryTests {

    @Autowired
    private TextileRepository textileRepository;


    @Test
    void testSave() {
        Textile newObj = new Textile();

        newObj.setPhoto("Slika 1");
        newObj.setPurpose("namena1");
        newObj.setAvailableQuantity(123);
        newObj.setPiecePrice(1200);
        Textile saved = textileRepository.save(newObj);
        assertNotNull(saved.getId());
    }

    @Test
    void testFindById() {
        Textile newObj = new Textile();

        newObj.setPhoto("Slika 1");
        newObj.setPurpose("namena1");
        newObj.setAvailableQuantity(123);
        newObj.setPiecePrice(1200);
        Textile saved = textileRepository.save(newObj);

        Textile found = textileRepository.findById(saved.getId()).orElse(null);
        assertNotNull(found);
        assertEquals(saved.getPhoto(), found.getPhoto());
        assertEquals(saved.getPurpose(), found.getPurpose());
        assertEquals(saved.getAvailableQuantity(), found.getAvailableQuantity());
        assertEquals(saved.getPiecePrice(), found.getPiecePrice());
        //assert other fields are equal as well
    }

    @Test
    void testGetAll() {
        Textile newObj = new Textile();

        newObj.setPhoto("Slika 1");
        newObj.setPurpose("namena1");
        newObj.setAvailableQuantity(123);
        newObj.setPiecePrice(1200);
        textileRepository.save(newObj);


        List<Textile> items = textileRepository.findAll();
        assertEquals(1, items.size());
    }

    @Test
    void testDeleteInvoiceBItem() {
        Textile newObj = new Textile();

        newObj.setPhoto("Slika 1");
        newObj.setPurpose("namena1");
        newObj.setAvailableQuantity(123);
        newObj.setPiecePrice(1200);
        Textile saved = textileRepository.save(newObj);

        textileRepository.deleteById(saved.getId());
        Optional<Textile> deleted = textileRepository.findById(saved.getId());
        assertFalse(deleted.isPresent());
    }

    @Test
    void testFindByPurpose() {
        Textile newObj = new Textile();

        newObj.setPhoto("Slika 1");
        newObj.setPurpose("namena1");
        newObj.setAvailableQuantity(123);
        newObj.setPiecePrice(1200);
        textileRepository.save(newObj);

        Textile newObj2 = new Textile();

        newObj2.setPhoto("Slika 2");
        newObj2.setPurpose("namena1");
        newObj2.setAvailableQuantity(1234);
        newObj2.setPiecePrice(12004);
        textileRepository.save(newObj2);

        List<Textile> items = textileRepository.findByPurpose(newObj.getPurpose());
        assertEquals(2, items.size());
        assertEquals(newObj.getPurpose(), items.get(0).getPurpose());
        assertEquals(newObj.getPurpose(), items.get(1).getPurpose());
    }

}