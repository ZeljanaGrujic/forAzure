package fon.bg.ac.rs.retailApp.services;

import fon.bg.ac.rs.retailApp.dtos.InvoiceBItemDto;
import fon.bg.ac.rs.retailApp.dtos.InvoiceItemDto;
import fon.bg.ac.rs.retailApp.models.InvoiceBItem;
import fon.bg.ac.rs.retailApp.models.InvoiceItem;

import java.util.List;

public interface InvoiceBItemService {
    InvoiceBItemDto saveInvoiceItem(InvoiceBItemDto invoiceItem);

    List<InvoiceBItem> findByInvoiceBuyingId(int id);

    InvoiceBItemDto findById(int id);
    public void deleteById(int id);

//    void deleteByTextileId(int invoicesellingid, int textileid);

    //    List<LocationDto> findByCountryid(int id);
}
