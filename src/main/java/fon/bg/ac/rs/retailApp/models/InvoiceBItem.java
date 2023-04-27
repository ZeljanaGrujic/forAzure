package fon.bg.ac.rs.retailApp.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import fon.bg.ac.rs.retailApp.dtos.InvoiceBuyingDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
//import jakarta.persistence.*;

@Entity
@Table(name = "invoiceb_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class InvoiceBItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private Integer quantity;
    private Integer totalCost;

    @ManyToOne
    @JoinColumn(name = "invoicebuyingid", insertable = false, updatable = false)
    private InvoiceBuying invoiceBuying;
    private Integer invoicebuyingid;

    @ManyToOne
    @JoinColumn(name = "textileid", insertable = false, updatable = false)
    private Textile textile;
    private Integer textileid;
}
