package fon.bg.ac.rs.retailApp.models;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
//import jakarta.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "invoice_selling")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class InvoiceSelling {

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
    @JoinColumn(name = "clientid", insertable = false, updatable = false)
    private Client client;
    private Integer clientid;

    private String specialRemarks;
//    private int totalCost;


//    @OneToMany(mappedBy = "invoiceSelling")
//    private List<InvoiceItem> invoiceItems;


//    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
//    @JoinTable(name = "invoices_textile", joinColumns = {@JoinColumn(name = "invoice_selling_id")},
//            inverseJoinColumns = {@JoinColumn(name = "textile_id")})
////            set ne dozvoljava ponavljanja, dok bih u listu mogla da ubacim isti element vise puta
//    //user moze da ima vise rola, a rola moze biti dodeljena ka vise user-a
//    //imacemo zajednicku tabelu user_role koja ce da sadrzi samo kombinaciju tipova
//    //fetchovacemo sve role usera pre nego sto mu dozvolimo negde da pristupi zato ce biti eager fetc
//    //kad obrisem roditelja cascadesType.All ce mu pobrisati svu decu, ali to necu tamo koristiti
//    Set<Textile> items = new HashSet<>();
//
//    @Override
//    public String toString() {
//        return "InvoiceSelling{" +
//                "id=" + id +
//                ", invoiceDate=" + invoiceDate +
//                ", invoiceStatus=" + invoiceStatus +
//                ", invoicestatusid=" + invoicestatusid +
//                ", client=" + client +
//                ", clientid=" + clientid +
//                ", specialRemarks='" + specialRemarks + '\'' +
//                '}';
//    }
}
