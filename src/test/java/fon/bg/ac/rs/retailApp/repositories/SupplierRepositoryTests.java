package fon.bg.ac.rs.retailApp.repositories;

import fon.bg.ac.rs.retailApp.models.Supplier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SupplierRepositoryTests {

    @Autowired
    private SupplierRepository supplierRepository;

    @Test
    void testSave() {
        Supplier newObj = new Supplier();
        newObj.setFullName("Dobavljac1");
        newObj.setCompanyName("Kompanija1");
        newObj.setPhone("Telefon1");
        newObj.setEmail("Zaposleni1@gmail.com");

        Supplier saved = supplierRepository.save(newObj);
        assertNotNull(saved.getId());
    }

    @Test
    void testFindById() {
        Supplier newObj = new Supplier();
        newObj.setFullName("Dobavljac1");
        newObj.setCompanyName("Kompanija1");
        newObj.setPhone("Telefon1");
        newObj.setEmail("Zaposleni1@gmail.com");

        Supplier saved = supplierRepository.save(newObj);

        Supplier found = supplierRepository.findById(saved.getId()).orElse(null);
        assertNotNull(found);
        assertEquals(saved.getFullName(), found.getFullName());
        assertEquals(saved.getCompanyName(), found.getCompanyName());
        assertEquals(saved.getPhone(), found.getPhone());
        assertEquals(saved.getEmail(), found.getEmail());
    }

    @Test
    void testGetAllEmployees() {
        Supplier newObj = new Supplier();
        newObj.setFullName("Dobavljac1");
        newObj.setCompanyName("Kompanija1");
        newObj.setPhone("Telefon1");
        newObj.setEmail("Zaposleni1@gmail.com");
        supplierRepository.save(newObj);

        Supplier newObj2 = new Supplier();
        newObj2.setFullName("Dobavljac1");
        newObj2.setCompanyName("Kompanija1");
        newObj2.setPhone("Telefon1");
        newObj2.setEmail("Zaposleni1@gmail.com");
        supplierRepository.save(newObj2);

        List<Supplier> items = supplierRepository.findAll();
        assertEquals(2, items.size());
    }

    @Test
    void testDeleteEmployee() {
        Supplier newObj = new Supplier();
        newObj.setFullName("Dobavljac1");
        newObj.setCompanyName("Kompanija1");
        newObj.setPhone("Telefon1");
        newObj.setEmail("Zaposleni1@gmail.com");

        Supplier saved = supplierRepository.save(newObj);

        supplierRepository.deleteById(saved.getId());
        Optional<Supplier> deletedEmployee = supplierRepository.findById(saved.getId());
        assertEquals(Optional.empty(), deletedEmployee);
    }
}