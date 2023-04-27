package fon.bg.ac.rs.retailApp.services;

import fon.bg.ac.rs.retailApp.dtos.TextileMakeDto;
import fon.bg.ac.rs.retailApp.models.TextileMake;
import fon.bg.ac.rs.retailApp.repositories.TextileMakeRepository;
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
class TextileMakeServiceTests {

    @Autowired
    private TextileMakeService service;
    @MockBean
    private TextileMakeRepository repository;

    @Test
    void getTextileMakes() {
        TextileMake newObj= new TextileMake();
        newObj.setId(1);
        newObj.setDetails("Detalji 1");
        newObj.setDescription("Opis 1");

        TextileMakeDto newObjDto= new TextileMakeDto();
        newObjDto.setId(1);
        newObjDto.setDetails("Detalji 1");
        newObjDto.setDescription("Opis 1");

        List<TextileMake> items = new ArrayList<>();
        items.add(newObj);
        when(repository.findAll()).thenReturn(items);

        List<TextileMakeDto> expectedItems = Arrays.asList(newObjDto);
        List<TextileMakeDto> foundItems = service.getTextileMakes();

        assertEquals(expectedItems, foundItems);
    }

    @Test
    void saveTextileMake() {
        TextileMake newObj= new TextileMake();
        newObj.setId(1);
        newObj.setDetails("Detalji 1");
        newObj.setDescription("Opis 1");

        TextileMakeDto newObjDto= new TextileMakeDto();
        newObjDto.setId(1);
        newObjDto.setDetails("Detalji 1");
        newObjDto.setDescription("Opis 1");

        when(repository.save(newObj)).thenReturn(newObj);

        TextileMakeDto expected = newObjDto;
        //i sada mogu samo da testiram biznis logiku servisa, i da se fokusiram na to a ne i na rad repozitorijuma
        //apstrahovala sam sve ono sto je ispod jer sam to vec istestirala u repositori testovima
        TextileMakeDto found = service.saveTextileMake(newObjDto);

        assertEquals(expected, found);
    }

    @Test
    void findById() {

        TextileMake newObj= new TextileMake();
        newObj.setId(1);
        newObj.setDetails("Detalji 1");
        newObj.setDescription("Opis 1");

        TextileMakeDto newObjDto= new TextileMakeDto();
        newObjDto.setId(1);
        newObjDto.setDetails("Detalji 1");
        newObjDto.setDescription("Opis 1");

        when(repository.findById(1)).thenReturn(Optional.ofNullable(newObj));

        TextileMakeDto expected = newObjDto;
        TextileMakeDto found = service.findById(1);

        assertEquals(expected, found);
    }

    @Test
    void deleteById() {
        TextileMake newObj= new TextileMake();
        newObj.setId(1);
        newObj.setDetails("Detalji 1");
        newObj.setDescription("Opis 1");

        TextileMakeDto newObjDto= new TextileMakeDto();
        newObjDto.setId(1);
        newObjDto.setDetails("Detalji 1");
        newObjDto.setDescription("Opis 1");

        when(repository.findById(newObj.getId())).thenReturn(Optional.ofNullable(newObj));
        service.deleteById(newObj.getId());
        verify(repository, times(1)).deleteById(newObj.getId());
    }
}