package fon.bg.ac.rs.retailApp.repositories;

import fon.bg.ac.rs.retailApp.models.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CountryRepositoryTests {

    @Autowired
    private CountryRepository countryRepository;

    @Test
    void testSaveCountry() {
        Country country = new Country();
        country.setCode("Kod1");
        country.setName("Region1");
        country.setCapital("Najveci grad regiona1");
        Country savedCountry = countryRepository.save(country);
        assertNotNull(savedCountry.getId());
    }

    @Test
    void testFindById() {
        Country country = new Country();
        country.setCode("Kod1");
        country.setName("Region1");
        country.setCapital("Najveci grad regiona1");
        Country savedCountry = countryRepository.save(country);

        Country foundCountry = countryRepository.findById(savedCountry.getId()).orElse(null);
        assertNotNull(foundCountry);
        assertEquals(savedCountry.getCode(), foundCountry.getCode());
        assertEquals(savedCountry.getName(), foundCountry.getName());
        assertEquals(savedCountry.getCapital(), foundCountry.getCapital());
    }

    @Test
    void testGetAllCountries() {
        Country country1 = new Country();
        country1.setCode("Kod1");
        country1.setName("Region1");
        country1.setCapital("Najveci grad regiona1");
        countryRepository.save(country1);

        Country country2 = new Country();
        country2.setCode("Kod2");
        country2.setName("Region2");
        country2.setCapital("Najveci grad regiona2");
        countryRepository.save(country2);

        List<Country> countries = countryRepository.findAll();
        assertEquals(2, countries.size());
    }

    @Test
    void testDeleteCountry() {
        Country country = new Country();
        country.setCode("Kod1");
        country.setName("Region1");
        country.setCapital("Najveci grad regiona1");
        Country savedCountry = countryRepository.save(country);

        countryRepository.deleteById(savedCountry.getId());
        Optional<Country> deletedCountry = countryRepository.findById(savedCountry.getId());
        assertFalse(deletedCountry.isPresent());
    }
}