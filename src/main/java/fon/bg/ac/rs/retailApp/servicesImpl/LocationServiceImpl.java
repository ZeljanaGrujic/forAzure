package fon.bg.ac.rs.retailApp.servicesImpl;

import fon.bg.ac.rs.retailApp.dtos.LocationDto;
import fon.bg.ac.rs.retailApp.models.Location;
import fon.bg.ac.rs.retailApp.repositories.LocationRepository;
import fon.bg.ac.rs.retailApp.services.LocationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired //ili na drugi nacin da inicijalizujem preko konstruktora
    private LocationRepository locationRepository;

    @Override
    public List<LocationDto> getLocations() {
        List<Location> all = locationRepository.findAll();
        List<LocationDto> dtos = all.stream()
                .map(d -> new LocationDto(d.getId(),
                        d.getCity(),
                        d.getAddress(),
                        d.getDetails(),
                        d.getCountry(),
                        d.getCountry().getId())).collect(Collectors.toList());

        return dtos;
    }

    @Override
    public LocationDto saveLocation(LocationDto location) {
        Location d = new Location();
        BeanUtils.copyProperties(location, d);

        Location saved = locationRepository.save(d);
        location.setId(saved.getId());

        return location;
    }

    @Override
    public LocationDto findById(int id) {
        Location find=locationRepository.findById(id).get();
        LocationDto d= new LocationDto();
        BeanUtils.copyProperties(find, d);

        return d;
    }

    @Override
    public void deleteById(int id) {
        locationRepository.deleteById(id);
    }

//    @Override
//    public List<LocationDto> findByCountryid(int id) {
//        return locationRepository.findByCountryid(id);
//    }

}
