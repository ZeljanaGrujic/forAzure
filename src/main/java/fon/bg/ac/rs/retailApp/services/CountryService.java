package fon.bg.ac.rs.retailApp.services;

import fon.bg.ac.rs.retailApp.dtos.CountryDto;
import fon.bg.ac.rs.retailApp.models.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    public List<CountryDto> getCountries();
    public CountryDto saveCountry(CountryDto country);

    public CountryDto findById(int id);

    public void deleteById(int id);
}
