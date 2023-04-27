package fon.bg.ac.rs.retailApp.services;

import fon.bg.ac.rs.retailApp.dtos.InvoiceItemDto;
import fon.bg.ac.rs.retailApp.dtos.InvoiceSellingDto;
import fon.bg.ac.rs.retailApp.models.InvoiceItem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceItemService {
    InvoiceItemDto saveInvoiceItem(InvoiceItemDto invoiceItem);

    List<InvoiceItem> findByInvoiceSellingId(int id);

    InvoiceItemDto findById(int id);
    public void deleteById(int id);

//    void deleteByTextileId(int invoicesellingid, int textileid);

    //    List<LocationDto> findByCountryid(int id);
}
