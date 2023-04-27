package fon.bg.ac.rs.retailApp.controllers;

import fon.bg.ac.rs.retailApp.dtos.CountryDto;
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

class CountryControllerTests {

    @Mock
    private CountryServiceImpl countryService;

    @Mock
    private LocationServiceImpl locationService;

    @Mock
    private Model model;

    @InjectMocks
    private CountryController countryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetCountries() {
        List<CountryDto> countries = new ArrayList<>();
        CountryDto countryDto = new CountryDto(1,"Kod 1", "Region 1", "Najveci grad 1",null);

        countries.add(countryDto);
        when(countryService.getCountries()).thenReturn(countries);

        String viewName = countryController.getCountries(model);

        assertEquals("Country", viewName);
        verify(model, times(1)).addAttribute(eq("countries"), eq(countries));
    }

    @Test
    void testAddNew() {
        CountryDto country = new CountryDto();
        when(countryService.saveCountry(country)).thenReturn(country);

        String viewName = countryController.addBew(country);

        assertEquals("redirect:/countries", viewName);
        verify(countryService, times(1)).saveCountry(country);
    }

    @Test
    void testFindById() {
        int id = 1;
        CountryDto country = new CountryDto();
        when(countryService.findById(id)).thenReturn(country);

        CountryDto result = countryController.findById(id);

        assertEquals(country, result);
        verify(countryService, times(2)).findById(id);
    }

    @Test
    void testUpdate() {
        CountryDto country = new CountryDto();
        when(countryService.saveCountry(country)).thenReturn(country);

        String viewName = countryController.update(country);
        assertEquals("redirect:/countries", viewName);
        verify(countryService, times(1)).saveCountry(country);
    }

    @Test
    void testDeleteById() {
        int id = 1;
        String viewName = countryController.deleteById(id);

        assertEquals("redirect:/countries", viewName);
        verify(countryService, times(1)).deleteById(id);
    }
}