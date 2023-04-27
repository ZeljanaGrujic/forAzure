package fon.bg.ac.rs.retailApp.services;

import fon.bg.ac.rs.retailApp.dtos.TextileStatusDto;
import fon.bg.ac.rs.retailApp.models.TextileStatus;
import fon.bg.ac.rs.retailApp.repositories.TextileStatusRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class TextileStatusServiceTests {

    @Autowired
    private TextileStatusService service;
    @MockBean
    private TextileStatusRepository repository;

    @Test
    void getTextileStatuses() {
        TextileStatus newObj= new TextileStatus();
        newObj.setId(1);
        newObj.setDetails("Detalji 1");
        newObj.setDescription("Opis 1");

        TextileStatusDto newObjDto= new TextileStatusDto();
        newObjDto.setId(1);
        newObjDto.setDetails("Detalji 1");
        newObjDto.setDescription("Opis 1");

        List<TextileStatus> items = new ArrayList<>();
        items.add(newObj);
        when(repository.findAll()).thenReturn(items);

        List<TextileStatusDto> expectedItems = Arrays.asList(newObjDto);
        List<TextileStatusDto> foundItems = service.getTextileStatuses();

        assertEquals(expectedItems, foundItems);
    }

    @Test
    void saveTextileStatus() {
        TextileStatus newObj= new TextileStatus();
        newObj.setId(1);
        newObj.setDetails("Detalji 1");
        newObj.setDescription("Opis 1");

        TextileStatusDto newObjDto= new TextileStatusDto();
        newObjDto.setId(1);
        newObjDto.setDetails("Detalji 1");
        newObjDto.setDescription("Opis 1");

        when(repository.save(newObj)).thenReturn(newObj);

        TextileStatusDto expected = newObjDto;
        //i sada mogu samo da testiram biznis logiku servisa, i da se fokusiram na to a ne i na rad repozitorijuma
        //apstrahovala sam sve ono sto je ispod jer sam to vec istestirala u repositori testovima
        TextileStatusDto found = service.saveTextileStatus(newObjDto);

        assertEquals(expected, found);
    }

    @Test
    void findById() {

        TextileStatus newObj= new TextileStatus();
        newObj.setId(1);
        newObj.setDetails("Detalji 1");
        newObj.setDescription("Opis 1");

        TextileStatusDto newObjDto= new TextileStatusDto();
        newObjDto.setId(1);
        newObjDto.setDetails("Detalji 1");
        newObjDto.setDescription("Opis 1");

        when(repository.findById(1)).thenReturn(Optional.ofNullable(newObj));

        TextileStatusDto expected = newObjDto;
        TextileStatusDto found = service.findById(1);

        assertEquals(expected, found);
    }
}