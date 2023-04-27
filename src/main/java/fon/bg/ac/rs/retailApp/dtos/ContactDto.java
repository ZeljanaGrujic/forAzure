package fon.bg.ac.rs.retailApp.dtos;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class ContactDto {


    private int id;
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
//    private String mobile;
    private String remarks;
}
