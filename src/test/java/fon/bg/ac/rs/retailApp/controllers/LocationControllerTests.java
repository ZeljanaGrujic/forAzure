package fon.bg.ac.rs.retailApp.controllers;

import fon.bg.ac.rs.retailApp.dtos.CountryDto;
import fon.bg.ac.rs.retailApp.dtos.LocationDto;
import fon.bg.ac.rs.retailApp.models.Country;
import fon.bg.ac.rs.retailApp.servicesImpl.CountryServiceImpl;
import fon.bg.ac.rs.retailApp.servicesImpl.LocationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LocationControllerTests {

    @Mock
    private LocationServiceImpl locationService;

    @Mock
    private CountryServiceImpl countryService;

    @Mock
    private Model model;

    @InjectMocks
    private LocationController locationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetLocations() {
        List<LocationDto> locations = new ArrayList<>();
        LocationDto locationDto = new LocationDto(1,"Grad 1", "Adresa 1", "Broj 1", new Country(1,"Kod 1", "Region 1", "Najveci grad 1",null),1);
        locations.add(locationDto);

        List<CountryDto> countries = new ArrayList<>();
        CountryDto countryDto = new CountryDto(1,"Kod 1", "Region 1", "Najveci grad 1",null);
        countries.add(countryDto);

        when(locationService.getLocations()).thenReturn(locations);
        when(countryService.getCountries()).thenReturn(countries);

        String viewName = locationController.getLocations(model);

        assertEquals("Location", viewName);
        verify(model, times(1)).addAttribute(eq("locations"), eq(locations));
        verify(model, times(1)).addAttribute(eq("countries"), eq(countries));
    }

    @Test
    void testAddNew() {
        LocationDto location = new LocationDto();
        when(locationService.saveLocation(location)).thenReturn(location);

        String viewName = locationController.addBew(location);

        assertEquals("redirect:/locations", viewName);
        verify(locationService, times(1)).saveLocation(location);
    }

    @Test
    void testFindById() {
        int id = 1;
        LocationDto location = new LocationDto(1,"Grad 1", "Adresa 1", "Broj 1", new Country(1,"Kod 1", "Region 1", "Najveci grad 1",null),1);
        when(locationService.findById(id)).thenReturn(location);

        LocationDto result = locationController.findById(id);

        assertEquals(location, result);
        verify(locationService, times(2)).findById(id);
    }

    @Test
    void testUpdate() {
        LocationDto location = new LocationDto(1,"Grad 1", "Adresa 1", "Broj 1", new Country(1,"Kod 1", "Region 1", "Najveci grad 1",null),1);
        when(locationService.saveLocation(location)).thenReturn(location);

        String viewName = locationController.update(location);

        assertEquals("redirect:/locations", viewName);
        verify(locationService, times(1)).saveLocation(location);
    }

    @Test
    void testDeleteById() {
        int id = 1;

        String viewName = locationController.deleteById(id);

        assertEquals("redirect:/locations", viewName);
        verify(locationService, times(1)).deleteById(id);
    }
}