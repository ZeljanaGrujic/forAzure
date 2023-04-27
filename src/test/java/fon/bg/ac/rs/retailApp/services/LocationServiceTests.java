package fon.bg.ac.rs.retailApp.services;

import fon.bg.ac.rs.retailApp.dtos.LocationDto;
import fon.bg.ac.rs.retailApp.models.Country;
import fon.bg.ac.rs.retailApp.models.Location;
import fon.bg.ac.rs.retailApp.repositories.LocationRepository;
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
class LocationServiceTests {

    @Autowired
    private LocationService service;
    @MockBean
    private LocationRepository repository;

    private final Location newObj = new Location(1,"Grad 1", "Adresa 1", "Broj 1", new Country(1,"Kod 1", "Region 1", "Najveci grad 1",null),1);

    private final LocationDto newObjDto = new LocationDto(1,"Grad 1", "Adresa 1", "Broj 1", new Country(1,"Kod 1", "Region 1", "Najveci grad 1",null),1);


    @Test
    void getLocations() {
        List<Location> items = Arrays.asList(newObj);
        when(repository.findAll()).thenReturn(items);

        List<LocationDto> expectedItems = Arrays.asList(newObjDto);
        List<LocationDto> foundItems = service.getLocations();

        assertEquals(expectedItems, foundItems);
    }

    @Test
    void saveLocation() {
        when(repository.save(newObj)).thenReturn(newObj);

        LocationDto expected = newObjDto;
        //i sada mogu samo da testiram biznis logiku servisa, i da se fokusiram na to a ne i na rad repozitorijuma
        //apstrahovala sam sve ono sto je ispod jer sam to vec istestirala u repositori testovima
        LocationDto found = service.saveLocation(newObjDto);

        assertEquals(expected, found);
    }

    @Test
    void findById() {
        when(repository.findById(1)).thenReturn(Optional.ofNullable(newObj));

        LocationDto expected = newObjDto;
        LocationDto found = service.findById(1);

        assertEquals(expected, found);
    }

    @Test
    void deleteById() {
        when(repository.findById(newObj.getId())).thenReturn(Optional.ofNullable(newObj));
        service.deleteById(newObj.getId());
        verify(repository, times(1)).deleteById(newObj.getId());
    }
}