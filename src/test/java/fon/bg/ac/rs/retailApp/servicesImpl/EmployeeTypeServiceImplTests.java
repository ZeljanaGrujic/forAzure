package fon.bg.ac.rs.retailApp.servicesImpl;

import fon.bg.ac.rs.retailApp.dtos.EmployeeTypeDto;
import fon.bg.ac.rs.retailApp.models.EmployeeType;
import fon.bg.ac.rs.retailApp.repositories.EmployeeTypeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeeTypeServiceImplTests {

    @Autowired
    private EmployeeTypeServiceImpl service;
    @MockBean
    private EmployeeTypeRepository repository;

    @Test
    void getEmployeeTypes() {
        EmployeeType newObj= new EmployeeType();
        newObj.setId(1);
        newObj.setDetails("Detalji 1");
        newObj.setDescription("Opis 1");

        EmployeeTypeDto newObjDto= new EmployeeTypeDto();
        newObjDto.setId(1);
        newObjDto.setDetails("Detalji 1");
        newObjDto.setDescription("Opis 1");

        List<EmployeeType> items = new ArrayList<>();
        items.add(newObj);
        when(repository.findAll()).thenReturn(items);

        List<EmployeeTypeDto> expectedItems = Arrays.asList(newObjDto);
        List<EmployeeTypeDto> foundItems = service.getEmployeeTypes();

        assertEquals(expectedItems, foundItems);

    }

    @Test
    void saveEmployeeType() {

        EmployeeType newObj= new EmployeeType();
        newObj.setId(1);
        newObj.setDetails("Detalji 1");
        newObj.setDescription("Opis 1");

        EmployeeTypeDto newObjDto= new EmployeeTypeDto();
        newObjDto.setId(1);
        newObjDto.setDetails("Detalji 1");
        newObjDto.setDescription("Opis 1");

        when(repository.save(newObj)).thenReturn(newObj);

        EmployeeTypeDto expected = newObjDto;
        //i sada mogu samo da testiram biznis logiku servisa, i da se fokusiram na to a ne i na rad repozitorijuma
        //apstrahovala sam sve ono sto je ispod jer sam to vec istestirala u repositori testovima
        EmployeeTypeDto found = service.saveEmployeeType(newObjDto);

        assertEquals(expected, found);
    }

    @Test
    void findById() {

        EmployeeType newObj= new EmployeeType();
        newObj.setId(1);
        newObj.setDetails("Detalji 1");
        newObj.setDescription("Opis 1");

        EmployeeTypeDto newObjDto= new EmployeeTypeDto();
        newObjDto.setId(1);
        newObjDto.setDetails("Detalji 1");
        newObjDto.setDescription("Opis 1");

        when(repository.findById(1)).thenReturn(Optional.ofNullable(newObj));

        EmployeeTypeDto expected = newObjDto;
        EmployeeTypeDto found = service.findById(1);

        assertEquals(expected, found);
    }

    @Test
    void deleteById() {

        EmployeeType newObj= new EmployeeType();
        newObj.setId(1);
        newObj.setDetails("Detalji 1");
        newObj.setDescription("Opis 1");

        EmployeeTypeDto newObjDto= new EmployeeTypeDto();
        newObjDto.setId(1);
        newObjDto.setDetails("Detalji 1");
        newObjDto.setDescription("Opis 1");

        when(repository.findById(newObj.getId())).thenReturn(Optional.ofNullable(newObj));
        service.deleteById(newObj.getId());
        verify(repository, times(1)).deleteById(newObj.getId());
    }
}