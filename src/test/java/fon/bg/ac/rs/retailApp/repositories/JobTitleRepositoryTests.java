package fon.bg.ac.rs.retailApp.repositories;

import fon.bg.ac.rs.retailApp.models.JobTitle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JobTitleRepositoryTests {

    @Autowired
    private JobTitleRepository jobTitleRepository;


    @Test
    void testSave() {
        JobTitle newObj = new JobTitle();
        newObj.setDescription("Opis1");
        newObj.setDetails("Detalji1");
        JobTitle saved = jobTitleRepository.save(newObj);
        assertNotNull(saved.getId());
    }

    @Test
    void testFindById() {
        JobTitle newObj = new JobTitle();
        newObj.setDescription("Opis1");
        newObj.setDetails("Detalji1");
        JobTitle saved = jobTitleRepository.save(newObj);

        JobTitle found = jobTitleRepository.findById(saved.getId()).orElse(null);
        assertNotNull(found);
        assertEquals(saved.getDescription(), found.getDescription());
        assertEquals(saved.getDetails(), found.getDetails());
        //assert other fields are equal as well
    }

    @Test
    void testGetAll() {
        JobTitle newObj1 = new JobTitle();
        newObj1.setDescription("Opis1");
        newObj1.setDetails("Detalji1");
        jobTitleRepository.save(newObj1);

        JobTitle newObj2 = new JobTitle();
        newObj2.setDescription("Opis2");
        newObj2.setDetails("Detalji2");
        jobTitleRepository.save(newObj2);

        List<JobTitle> items = jobTitleRepository.findAll();
        assertEquals(2, items.size());
    }

    @Test
    void testDelete() {
        JobTitle newObj = new JobTitle();
        newObj.setDescription("Opis1");
        newObj.setDetails("Detalji1");
        JobTitle saved = jobTitleRepository.save(newObj);

        jobTitleRepository.deleteById(saved.getId());
        Optional<JobTitle> deleted = jobTitleRepository.findById(saved.getId());
        assertFalse(deleted.isPresent());
    }
}