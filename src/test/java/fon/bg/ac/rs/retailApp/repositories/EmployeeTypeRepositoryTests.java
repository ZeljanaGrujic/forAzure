package fon.bg.ac.rs.retailApp.repositories;

import fon.bg.ac.rs.retailApp.models.EmployeeType;
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
public class EmployeeTypeRepositoryTests {

    @Autowired
    private EmployeeTypeRepository employeeTypeRepository;

    @Test
    void testSaveEmployeeType() {
        EmployeeType employeeType = new EmployeeType();
        employeeType.setDescription("Opis1");
        employeeType.setDetails("Detalji1");
        EmployeeType savedEmployeeType = employeeTypeRepository.save(employeeType);
        assertNotNull(savedEmployeeType.getId());
    }


    @Test
    void testFindById() {
        EmployeeType employeeType = new EmployeeType();
        employeeType.setDescription("Opis1");
        employeeType.setDetails("Detalji1");
        EmployeeType savedEmployeeType = employeeTypeRepository.save(employeeType);

        EmployeeType foundEmployeeType = employeeTypeRepository.findById(savedEmployeeType.getId()).orElse(null);;
        assertNotNull(foundEmployeeType);

    }

    @Test
    void testGetAllEmployees() {
        EmployeeType employeeType1 = new EmployeeType();
        employeeType1.setDescription("Opis1");
        employeeType1.setDetails("Detalji1");
        employeeTypeRepository.save(employeeType1);

        EmployeeType employeeType2 = new EmployeeType();
        employeeType2.setDescription("Opis2");
        employeeType2.setDetails("Detalji2");
        employeeTypeRepository.save(employeeType2);

        List<EmployeeType> employeeTypes = employeeTypeRepository.findAll();
        assertEquals(2, employeeTypes.size());
    }

    @Test
    void testDeleteEmployee() {
        EmployeeType employeeType = new EmployeeType();
        employeeType.setDescription("Opis1");
        employeeType.setDetails("Detalji1");
        EmployeeType savedEmployeeType = employeeTypeRepository.save(employeeType);

        employeeTypeRepository.deleteById(savedEmployeeType.getId());
        Optional<EmployeeType> deletedEmployee = employeeTypeRepository.findById(savedEmployeeType.getId());
        assertEquals(Optional.empty(), deletedEmployee);
    }
}