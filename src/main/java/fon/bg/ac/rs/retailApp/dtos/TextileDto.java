package fon.bg.ac.rs.retailApp.dtos;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fon.bg.ac.rs.retailApp.models.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextileDto {


    private Integer id;
    private String uniqueCode;
    private int piecePrice;
    private String specialDescription;
    private int availableQuantity;
    private String photo;
    private String purpose;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date acquisitionDate; //date for when it arrived at our store


    private TextileType textileType;
    private Integer textiletypeid;



    private TextileMake textleMake;
    private Integer textilemakeid;



    private TextileModel textileModel;
    private Integer textilemodelid;



    private TextileStatus textileStatus;
    private Integer textilestatusid;


    private Employee inCharge;
    private Integer employeeid;


    private Supplier supplier;
    private Integer supplierid;

}
