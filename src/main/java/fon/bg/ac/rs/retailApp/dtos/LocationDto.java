package fon.bg.ac.rs.retailApp.dtos;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fon.bg.ac.rs.retailApp.models.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
//import jakarta.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class LocationDto {


    private Integer id;
    private String city;
    private String address;
    private String details;

    private Country country;
    private Integer countryid;
}
