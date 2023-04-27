package fon.bg.ac.rs.retailApp.services;

import fon.bg.ac.rs.retailApp.dtos.CountryDto;
import fon.bg.ac.rs.retailApp.models.Country;
import fon.bg.ac.rs.retailApp.repositories.CountryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class CountryServiceTests {

    @Autowired
    private CountryService service;
    @MockBean
    private CountryRepository repository;

    private final Country newObj = new Country(1,"Kod 1", "Region 1", "Najveci grad 1",null);

    private final CountryDto newObjDto = new CountryDto(1,"Kod 1", "Region 1", "Najveci grad 1",null);


    @Test
    void getCountries() {
        List<Country> items = Arrays.asList(newObj);
        when(repository.findAll()).thenReturn(items);

        List<CountryDto> expectedItems = Arrays.asList(newObjDto);
        List<CountryDto> foundItems = service.getCountries();

        assertEquals(expectedItems, foundItems);
    }

    @Test
    void saveCountry() {
        when(repository.save(newObj)).thenReturn(newObj);

        CountryDto expected = newObjDto;
        //i sada mogu samo da testiram biznis logiku servisa, i da se fokusiram na to a ne i na rad repozitorijuma
        //apstrahovala sam sve ono sto je ispod jer sam to vec istestirala u repositori testovima
        CountryDto found = service.saveCountry(newObjDto);

        assertEquals(expected, found);
    }

    @Test
    void findById() {
        when(repository.findById(1)).thenReturn(Optional.ofNullable(newObj));

        CountryDto expected = newObjDto;
        CountryDto found = service.findById(1);

        assertEquals(expected, found);
    }

    @Test
    void deleteById() {
        when(repository.findById(newObj.getId())).thenReturn(Optional.ofNullable(newObj));
        service.deleteById(newObj.getId());
        verify(repository, times(1)).deleteById(newObj.getId());
    }
}