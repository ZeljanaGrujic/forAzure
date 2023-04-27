package fon.bg.ac.rs.retailApp.dtos;

import fon.bg.ac.rs.retailApp.models.InvoiceSelling;
import fon.bg.ac.rs.retailApp.models.Textile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceItemDto {


    private Integer id;

    private Integer quantity;
    private Integer totalCost;


    private InvoiceSelling invoiceSelling;
    private Integer invoicesellingid;


    private Textile textile;
    private Integer textileid;
}
