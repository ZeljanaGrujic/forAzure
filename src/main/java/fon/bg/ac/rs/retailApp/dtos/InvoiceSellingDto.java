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
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class InvoiceSellingDto {


    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date invoiceDate;


    private InvoiceStatus invoiceStatus;
    private Integer invoicestatusid;


    private Client client;
    private Integer clientid;

    private String specialRemarks;
//    private int totalCost;


//    private List<InvoiceItem> invoiceItems;
//    Set<Textile> items = new HashSet<>();

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
