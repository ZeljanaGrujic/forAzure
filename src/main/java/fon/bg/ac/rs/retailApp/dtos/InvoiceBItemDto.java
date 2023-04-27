package fon.bg.ac.rs.retailApp.dtos;

import fon.bg.ac.rs.retailApp.models.InvoiceBuying;
import fon.bg.ac.rs.retailApp.models.InvoiceSelling;
import fon.bg.ac.rs.retailApp.models.Textile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceBItemDto {


    private Integer id;

    private Integer quantity;
    private Integer totalCost;


    private InvoiceBuying invoiceBuying;
    private Integer invoicebuyingid;


    private Textile textile;
    private Integer textileid;
}
