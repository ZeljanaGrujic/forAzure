package fon.bg.ac.rs.retailApp.services;

import fon.bg.ac.rs.retailApp.models.Employee;
import fon.bg.ac.rs.retailApp.repositories.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeeServiceTests {

    @Autowired
    private EmployeeService service;
    @MockBean
    private EmployeeRepository repository;



    @Test
    void getEmployees() {
        Employee newObj = new Employee();
        newObj.setUsername("zaposleni1");
        newObj.setPhoto("slika1");
        newObj.setPhone("Telefon1");
        newObj.setEmail("Zaposleni1@gmail.com");

        List<Employee> items= new ArrayList<>();
        items.add(newObj);
        when(repository.findAll()).thenReturn(items);

        List<Employee> foundItems = service.getEmployees();
        assertEquals(items, foundItems);
    }

    @Test
    void saveEmployee() {
        Employee newObj = new Employee();
        newObj.setUsername("zaposleni1");
        newObj.setPhoto("slika1");
        newObj.setPhone("Telefon1");
        newObj.setEmail("Zaposleni1@gmail.com");

        when(repository.save(newObj)).thenReturn(newObj);


        //i sada mogu samo da testiram biznis logiku servisa, i da se fokusiram na to a ne i na rad repozitorijuma
        //apstrahovala sam sve ono sto je ispod jer sam to vec istestirala u repositori testovima
        Employee found = service.saveEmployee(newObj);

        assertEquals(newObj, found);
    }

    @Test
    void findById() {

        Employee newObj = new Employee();
        newObj.setId(1);
        newObj.setUsername("zaposleni1");
        newObj.setPhoto("slika1");
        newObj.setPhone("Telefon1");
        newObj.setEmail("Zaposleni1@gmail.com");
        when(repository.findById(1)).thenReturn(Optional.ofNullable(newObj));


        Employee found = service.findById(1).orElse(null);

        assertNotNull(found);
        assertEquals(newObj, found);
    }

    @Test
    void deleteById() {
        Employee newObj = new Employee();
        newObj.setId(1);
        newObj.setUsername("zaposleni1");
        newObj.setPhoto("slika1");
        newObj.setPhone("Telefon1");
        newObj.setEmail("Zaposleni1@gmail.com");
        when(repository.findById(newObj.getId())).thenReturn(Optional.ofNullable(newObj));
        service.deleteById(newObj.getId());
        verify(repository, times(1)).deleteById(newObj.getId());
    }

    @Test
    void findByUsername() {
        Employee newObj = new Employee();
        newObj.setId(1);
        newObj.setUsername("zaposleni1");
        newObj.setPhoto("slika1");
        newObj.setPhone("Telefon1");
        newObj.setEmail("Zaposleni1@gmail.com");
        when(repository.findByUsername(newObj.getUsername())).thenReturn(newObj);


        Employee found = service.findByUsername(newObj.getUsername());

        assertNotNull(found);
        assertEquals(newObj, found);
    }

}