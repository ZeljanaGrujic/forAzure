package fon.bg.ac.rs.retailApp.services;

import fon.bg.ac.rs.retailApp.dtos.TextileTypeDto;
import fon.bg.ac.rs.retailApp.models.TextileType;
import fon.bg.ac.rs.retailApp.repositories.TextileTypeRepository;
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
class TextileTypeServiceTests {

    @Autowired
    private TextileTypeService service;
    @MockBean
    private TextileTypeRepository repository;

    @Test
    void getTextileTypes() {
        TextileType newObj= new TextileType();
        newObj.setId(1);
        newObj.setDetails("Detalji 1");
        newObj.setDescription("Opis 1");

        TextileTypeDto newObjDto= new TextileTypeDto();
        newObjDto.setId(1);
        newObjDto.setDetails("Detalji 1");
        newObjDto.setDescription("Opis 1");

        List<TextileType> items = new ArrayList<>();
        items.add(newObj);
        when(repository.findAll()).thenReturn(items);

        List<TextileTypeDto> expectedItems = Arrays.asList(newObjDto);
        List<TextileTypeDto> foundItems = service.getTextileTypes();

        assertEquals(expectedItems, foundItems);
    }

    @Test
    void saveTextileType() {

        TextileType newObj= new TextileType();
        newObj.setId(1);
        newObj.setDetails("Detalji 1");
        newObj.setDescription("Opis 1");

        TextileTypeDto newObjDto= new TextileTypeDto();
        newObjDto.setId(1);
        newObjDto.setDetails("Detalji 1");
        newObjDto.setDescription("Opis 1");

        when(repository.save(newObj)).thenReturn(newObj);

        TextileTypeDto expected = newObjDto;
        //i sada mogu samo da testiram biznis logiku servisa, i da se fokusiram na to a ne i na rad repozitorijuma
        //apstrahovala sam sve ono sto je ispod jer sam to vec istestirala u repositori testovima
        TextileTypeDto found = service.saveTextileType(newObjDto);

        assertEquals(expected, found);
    }

    @Test
    void findById() {
        TextileType newObj= new TextileType();
        newObj.setId(1);
        newObj.setDetails("Detalji 1");
        newObj.setDescription("Opis 1");

        TextileTypeDto newObjDto= new TextileTypeDto();
        newObjDto.setId(1);
        newObjDto.setDetails("Detalji 1");
        newObjDto.setDescription("Opis 1");

        when(repository.findById(1)).thenReturn(Optional.ofNullable(newObj));

        TextileTypeDto expected = newObjDto;
        TextileTypeDto found = service.findById(1);

        assertEquals(expected, found);
    }

    @Test
    void deleteById() {

        TextileType newObj= new TextileType();
        newObj.setId(1);
        newObj.setDetails("Detalji 1");
        newObj.setDescription("Opis 1");

        TextileTypeDto newObjDto= new TextileTypeDto();
        newObjDto.setId(1);
        newObjDto.setDetails("Detalji 1");
        newObjDto.setDescription("Opis 1");

        when(repository.findById(newObj.getId())).thenReturn(Optional.ofNullable(newObj));
        service.deleteById(newObj.getId());
        verify(repository, times(1)).deleteById(newObj.getId());
    }
}