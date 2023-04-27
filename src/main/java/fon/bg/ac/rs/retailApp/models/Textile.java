package fon.bg.ac.rs.retailApp.models;

import java.util.Date;

import javax.persistence.*;
//import jakarta.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "textile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Textile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String uniqueCode;
    private int piecePrice;
    private String specialDescription;
    private int availableQuantity;
    private String photo;
    private String purpose;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date acquisitionDate; //date for when it arrived at our store

    @ManyToOne
    @JoinColumn(name = "textiletypeid", insertable = false, updatable = false)
    private TextileType textileType;
    private Integer textiletypeid;


    @ManyToOne
    @JoinColumn(name = "textilemakeid", insertable = false, updatable = false)
    private TextileMake textleMake;
    private Integer textilemakeid;


    @ManyToOne
    @JoinColumn(name = "textilemodelid", insertable = false, updatable = false)
    private TextileModel textileModel;
    private Integer textilemodelid;


    @ManyToOne
    @JoinColumn(name = "textilestatusid", insertable = false, updatable = false)
    private TextileStatus textileStatus;
    private Integer textilestatusid;

    @ManyToOne
    @JoinColumn(name = "employeeid", insertable = false, updatable = false)
    private Employee inCharge;
    private Integer employeeid;

    @ManyToOne
    @JoinColumn(name = "supplierid", insertable = false, updatable = false)
    private Supplier supplier;
    private Integer supplierid;

}
