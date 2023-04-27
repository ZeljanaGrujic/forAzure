package fon.bg.ac.rs.retailApp.services;

import fon.bg.ac.rs.retailApp.dtos.LocationDto;
import fon.bg.ac.rs.retailApp.models.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    List<LocationDto> getLocations();

    LocationDto saveLocation(LocationDto location);

    LocationDto findById(int id);

    void deleteById(int id);

//    List<LocationDto> findByCountryid(int id);
}
