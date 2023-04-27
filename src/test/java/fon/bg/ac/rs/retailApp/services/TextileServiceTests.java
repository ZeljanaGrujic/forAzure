package fon.bg.ac.rs.retailApp.services;

import fon.bg.ac.rs.retailApp.dtos.TextileDto;
import fon.bg.ac.rs.retailApp.models.*;
import fon.bg.ac.rs.retailApp.repositories.TextileRepository;
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
class TextileServiceTests {

    @Autowired
    private TextileService service;
    @MockBean
    private TextileRepository repository;

    @Test
    void getTextiles() {

        Textile newObj= new Textile();
        newObj.setId(1);
        newObj.setPurpose("namena 1");
        newObj.setPiecePrice(2000);
        newObj.setPhoto("slika 1");
        newObj.setAvailableQuantity(20);
        newObj.setSpecialDescription("Napomena 1");
        newObj.setTextileType(new TextileType());
        newObj.setTextileModel(new TextileModel());
        newObj.setTextleMake(new TextileMake());
        newObj.setTextileStatus(new TextileStatus());
        newObj.setInCharge(new Employee());
        newObj.setSupplier(new Supplier());

        TextileDto newObjDto= new TextileDto();
        newObjDto.setId(1);
        newObjDto.setPurpose("namena 1");
        newObjDto.setPiecePrice(2000);
        newObjDto.setPhoto("slika 1");
        newObjDto.setAvailableQuantity(20);
        newObjDto.setSpecialDescription("Napomena 1");
        newObjDto.setTextileType(new TextileType());
        newObjDto.setTextileModel(new TextileModel());
        newObjDto.setTextleMake(new TextileMake());
        newObjDto.setTextileStatus(new TextileStatus());
        newObjDto.setInCharge(new Employee());
        newObjDto.setSupplier(new Supplier());

        List<Textile> items = new ArrayList<>();
        items.add(newObj);
        when(repository.findAll()).thenReturn(items);

        List<TextileDto> expectedItems = Arrays.asList(newObjDto);
        List<TextileDto> foundItems = service.getTextiles();

        assertEquals(expectedItems, foundItems);
    }

    @Test
    void saveTextile() {
        Textile newObj= new Textile();
        newObj.setId(1);
        newObj.setPurpose("namena 1");
        newObj.setPiecePrice(2000);
        newObj.setPhoto("slika 1");
        newObj.setAvailableQuantity(20);
        newObj.setSpecialDescription("Napomena 1");

        TextileDto newObjDto= new TextileDto();
        newObjDto.setId(1);
        newObjDto.setPurpose("namena 1");
        newObjDto.setPiecePrice(2000);
        newObjDto.setPhoto("slika 1");
        newObjDto.setAvailableQuantity(20);
        newObjDto.setSpecialDescription("Napomena 1");

        when(repository.save(newObj)).thenReturn(newObj);

        TextileDto expected = newObjDto;
        //i sada mogu samo da testiram biznis logiku servisa, i da se fokusiram na to a ne i na rad repozitorijuma
        //apstrahovala sam sve ono sto je ispod jer sam to vec istestirala u repositori testovima
        TextileDto found = service.saveTextile(newObjDto);

        assertEquals(expected, found);
    }

    @Test
    void findById() {

        Textile newObj= new Textile();
        newObj.setId(1);
        newObj.setPurpose("namena 1");
        newObj.setPiecePrice(2000);
        newObj.setPhoto("slika 1");
        newObj.setAvailableQuantity(20);
        newObj.setSpecialDescription("Napomena 1");

        TextileDto newObjDto= new TextileDto();
        newObjDto.setId(1);
        newObjDto.setPurpose("namena 1");
        newObjDto.setPiecePrice(2000);
        newObjDto.setPhoto("slika 1");
        newObjDto.setAvailableQuantity(20);
        newObjDto.setSpecialDescription("Napomena 1");

        when(repository.findById(1)).thenReturn(Optional.ofNullable(newObj));

        TextileDto expected = newObjDto;
        TextileDto found = service.findById(1);

        assertEquals(expected, found);
    }

    @Test
    void deleteById() {
        Textile newObj= new Textile();
        newObj.setId(1);
        newObj.setPurpose("namena 1");
        newObj.setPiecePrice(2000);
        newObj.setPhoto("slika 1");
        newObj.setAvailableQuantity(20);
        newObj.setSpecialDescription("Napomena 1");

        TextileDto newObjDto= new TextileDto();
        newObjDto.setId(1);
        newObjDto.setPurpose("namena 1");
        newObjDto.setPiecePrice(2000);
        newObjDto.setPhoto("slika 1");
        newObjDto.setAvailableQuantity(20);
        newObjDto.setSpecialDescription("Napomena 1");

        when(repository.findById(newObj.getId())).thenReturn(Optional.ofNullable(newObj));
        service.deleteById(newObj.getId());
        verify(repository, times(1)).deleteById(newObj.getId());
    }

    @Test
    void findByPurpose() {

        Textile newObj= new Textile();
        newObj.setId(1);
        newObj.setPurpose("namena 1");
        newObj.setPiecePrice(2000);
        newObj.setPhoto("slika 1");
        newObj.setAvailableQuantity(20);
        newObj.setSpecialDescription("Napomena 1");
        newObj.setTextileType(new TextileType());
        newObj.setTextileModel(new TextileModel());
        newObj.setTextleMake(new TextileMake());
        newObj.setTextileStatus(new TextileStatus());
        newObj.setInCharge(new Employee());
        newObj.setSupplier(new Supplier());

        TextileDto newObjDto= new TextileDto();
        newObjDto.setId(1);
        newObjDto.setPurpose("namena 1");
        newObjDto.setPiecePrice(2000);
        newObjDto.setPhoto("slika 1");
        newObjDto.setAvailableQuantity(20);
        newObjDto.setSpecialDescription("Napomena 1");
        newObjDto.setTextileType(new TextileType());
        newObjDto.setTextileModel(new TextileModel());
        newObjDto.setTextleMake(new TextileMake());
        newObjDto.setTextileStatus(new TextileStatus());
        newObjDto.setInCharge(new Employee());
        newObjDto.setSupplier(new Supplier());

        List<Textile> items= new ArrayList<>();
        items.add(newObj);
        when(repository.findByPurpose(newObj.getPurpose())).thenReturn(items);

        TextileDto expected = newObjDto;
        TextileDto found = service.findByPurpose(newObjDto.getPurpose()).get(0);

        assertEquals(expected, found);
    }
}