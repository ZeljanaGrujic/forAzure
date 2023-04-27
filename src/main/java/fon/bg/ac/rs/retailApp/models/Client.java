package fon.bg.ac.rs.retailApp.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
//import jakarta.persistence.*;

import javax.persistence.Entity;

@Entity
@Table(name = "client")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String fullName;
//    private String surname;
    private String phone;
    private String email;
//    private String city;
//    private String address;
    private String details; //if someone owes something or similar...


//    @ManyToOne
//    @JoinColumn(name = "countryid", insertable = false, updatable = false)
//    private Country country;
//    private Integer countryid;

    @ManyToOne
    @JoinColumn(name = "locationid", insertable = false, updatable = false)
    private Location location;
    private Integer locationid;

    //maybe I won't need country for client, I am saving city and address for him
    //maybe country is too much

}
