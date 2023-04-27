package fon.bg.ac.rs.retailApp.servicesImpl;

import fon.bg.ac.rs.retailApp.dtos.JobTitleDto;
import fon.bg.ac.rs.retailApp.models.JobTitle;
import fon.bg.ac.rs.retailApp.repositories.JobTitleRepository;
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
class JobTitleServiceImplTests {

    @Autowired
    private JobTitleServiceImpl service;
    @MockBean
    private JobTitleRepository repository;

    @Test
    void getJobTitles() {

        JobTitle newObj= new JobTitle();
        newObj.setId(1);
        newObj.setDetails("Detalji 1");
        newObj.setDescription("Opis 1");

        JobTitleDto newObjDto= new JobTitleDto();
        newObjDto.setId(1);
        newObjDto.setDetails("Detalji 1");
        newObjDto.setDescription("Opis 1");

        List<JobTitle> items = new ArrayList<>();
        items.add(newObj);
        when(repository.findAll()).thenReturn(items);

        List<JobTitleDto> expectedItems = Arrays.asList(newObjDto);
        List<JobTitleDto> foundItems = service.getJobTitles();

        assertEquals(expectedItems, foundItems);
    }

    @Test
    void saveJobTitle() {
        JobTitle newObj= new JobTitle();
        newObj.setId(1);
        newObj.setDetails("Detalji 1");
        newObj.setDescription("Opis 1");

        JobTitleDto newObjDto= new JobTitleDto();
        newObjDto.setId(1);
        newObjDto.setDetails("Detalji 1");
        newObjDto.setDescription("Opis 1");

        when(repository.save(newObj)).thenReturn(newObj);

        JobTitleDto expected = newObjDto;
        //i sada mogu samo da testiram biznis logiku servisa, i da se fokusiram na to a ne i na rad repozitorijuma
        //apstrahovala sam sve ono sto je ispod jer sam to vec istestirala u repositori testovima
        JobTitleDto found = service.saveJobTitle(newObjDto);

        assertEquals(expected, found);
    }

    @Test
    void findById() {

        JobTitle newObj= new JobTitle();
        newObj.setId(1);
        newObj.setDetails("Detalji 1");
        newObj.setDescription("Opis 1");

        JobTitleDto newObjDto= new JobTitleDto();
        newObjDto.setId(1);
        newObjDto.setDetails("Detalji 1");
        newObjDto.setDescription("Opis 1");

        when(repository.findById(1)).thenReturn(Optional.ofNullable(newObj));

        JobTitleDto expected = newObjDto;
        JobTitleDto found = service.findById(1);

        assertEquals(expected, found);
    }

    @Test
    void deleteById() {

        JobTitle newObj= new JobTitle();
        newObj.setId(1);
        newObj.setDetails("Detalji 1");
        newObj.setDescription("Opis 1");

        JobTitleDto newObjDto= new JobTitleDto();
        newObjDto.setId(1);
        newObjDto.setDetails("Detalji 1");
        newObjDto.setDescription("Opis 1");

        when(repository.findById(newObj.getId())).thenReturn(Optional.ofNullable(newObj));
        service.deleteById(newObj.getId());
        verify(repository, times(1)).deleteById(newObj.getId());
    }
}