package fon.bg.ac.rs.retailApp.repositories;

import fon.bg.ac.rs.retailApp.models.Employee;
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
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void testSaveEmployee() {
        Employee employee = new Employee();
        employee.setUsername("zaposleni1");
        employee.setPhoto("slika1");
        employee.setPhone("Telefon1");
        employee.setEmail("Zaposleni1@gmail.com");

        Employee savedEmployee = employeeRepository.save(employee);
        assertNotNull(savedEmployee.getId());
    }

    @Test
    void testFindByUsername() {
        Employee employee = new Employee();
        employee.setUsername("zaposleni1");
        employee.setPhoto("slika1");
        employee.setPhone("Telefon1");
        employee.setEmail("Zaposleni1@gmail.com");
        Employee savedEmployee = employeeRepository.save(employee);

        Employee foundEmployee = employeeRepository.findByUsername(savedEmployee.getUsername());
        assertNotNull(foundEmployee);
        assertEquals(savedEmployee.getUsername(), foundEmployee.getUsername());
        assertEquals(savedEmployee.getPhoto(), foundEmployee.getPhoto());
        assertEquals(savedEmployee.getPhone(), foundEmployee.getPhone());
        assertEquals(savedEmployee.getEmail(), foundEmployee.getEmail());
    }


    @Test
    void testFindById() {
        Employee employee = new Employee();
        employee.setUsername("zaposleni1");
        employee.setPhoto("slika1");
        employee.setPhone("Telefon1");
        employee.setEmail("Zaposleni1@gmail.com");
        Employee savedEmployee = employeeRepository.save(employee);

        Employee foundEmployee = employeeRepository.findByUsername(savedEmployee.getUsername());
        assertNotNull(foundEmployee);
        assertEquals(savedEmployee.getUsername(), foundEmployee.getUsername());
        assertEquals(savedEmployee.getPhoto(), foundEmployee.getPhoto());
        assertEquals(savedEmployee.getPhone(), foundEmployee.getPhone());
        assertEquals(savedEmployee.getEmail(), foundEmployee.getEmail());
    }

    @Test
    void testGetAllEmployees() {
        Employee employee1 = new Employee();
        employee1.setUsername("zaposleni1");
        employee1.setPhoto("slika1");
        employee1.setPhone("Telefon1");
        employee1.setEmail("Zaposleni1@gmail.com");
        employeeRepository.save(employee1);

        Employee employee2 = new Employee();
        employee2.setUsername("zaposleni2");
        employee2.setPhoto("slika2");
        employee2.setPhone("Telefon2");
        employee2.setEmail("Zaposleni2@gmail.com");
        employeeRepository.save(employee2);

        List<Employee> employees = employeeRepository.findAll();
        assertEquals(2, employees.size());
    }

    @Test
    void testDeleteEmployee() {
        Employee employee = new Employee();
        employee.setUsername("zaposleni1");
        employee.setPhoto("slika1");
        employee.setPhone("Telefon1");
        employee.setEmail("Zaposleni1@gmail.com");
        Employee savedEmployee = employeeRepository.save(employee);

        employeeRepository.deleteById(savedEmployee.getId());
        Optional<Employee> deletedEmployee = employeeRepository.findById(savedEmployee.getId());
        assertEquals(Optional.empty(), deletedEmployee);
    }
}