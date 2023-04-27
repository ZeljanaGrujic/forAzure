package fon.bg.ac.rs.retailApp.servicesImpl;

import fon.bg.ac.rs.retailApp.dtos.CountryDto;
import fon.bg.ac.rs.retailApp.models.Country;
import fon.bg.ac.rs.retailApp.repositories.CountryRepository;
import fon.bg.ac.rs.retailApp.services.CountryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired //ili na drugi nacin da inicijalizujem preko konstruktora
    private CountryRepository countryRepository;

    @Override
    public List<CountryDto> getCountries() {

        List<Country> all = countryRepository.findAll();
        List<CountryDto> countries = all.stream()
                .map(c -> new CountryDto(c.getId(),
                        c.getCode(),
                        c.getName(),
                        c.getCapital(),
                        c.getLocations())).collect(Collectors.toList());

        return countries;
    }

    @Override
    public CountryDto saveCountry(CountryDto country) {
        Country c = new Country();
        BeanUtils.copyProperties(country, c);

        Country saved = countryRepository.save(c);
        country.setId(saved.getId());
        return country;
    }

    //Get Country By Id
    public CountryDto findById(int id) {

        Country find=countryRepository.findById(id).get();
        CountryDto country= new CountryDto();
        BeanUtils.copyProperties(find, country);

        return country;
    }

    @Override
    public void deleteById(int id) {

        countryRepository.deleteById(id);
    }


}
