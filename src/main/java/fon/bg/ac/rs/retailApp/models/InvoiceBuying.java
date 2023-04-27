package fon.bg.ac.rs.retailApp.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
//import jakarta.persistence.*;

import fon.bg.ac.rs.retailApp.dtos.TextileDto;
import fon.bg.ac.rs.retailApp.security.models.Role;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "invoice_buying")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class InvoiceBuying {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date invoiceDate;

    @ManyToOne
    @JoinColumn(name = "invoicestatusid", insertable = false, updatable = false)
    private InvoiceStatus invoiceStatus;
    private Integer invoicestatusid;

    @ManyToOne
    @JoinColumn(name = "supplierid", insertable = false, updatable = false)
    private Supplier supplier;
    private Integer supplierid;


    private String specialRemarks;
//    private int totalCost;


//    @ManyToOne
//    @JoinColumn(name = "textileid", insertable = false, updatable = false)
//    private Textile textile;
//    private Integer textileid;

//    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
//    @JoinTable(name="invoiceb_textile", joinColumns = {@JoinColumn(name = "invoice_buying_id")},
//            inverseJoinColumns = {@JoinColumn (name = "textile_id")})
////            set ne dozvoljava ponavljanja, dok bih u listu mogla da ubacim isti element vise puta
//    //user moze da ima vise rola, a rola moze biti dodeljena ka vise user-a
//    //imacemo zajednicku tabelu user_role koja ce da sadrzi samo kombinaciju tipova
//    //fetchovacemo sve role usera pre nego sto mu dozvolimo negde da pristupi zato ce biti eager fetc
//    //kad obrisem roditelja cascadesType.All ce mu pobrisati svu decu, ali to necu tamo koristiti
//    Set<Textile> items= new HashSet<>();
}
