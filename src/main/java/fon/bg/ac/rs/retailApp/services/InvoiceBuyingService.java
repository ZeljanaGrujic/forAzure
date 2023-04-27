package fon.bg.ac.rs.retailApp.services;

import fon.bg.ac.rs.retailApp.dtos.InvoiceBuyingDto;
import fon.bg.ac.rs.retailApp.models.InvoiceBuying;

import java.util.List;
import java.util.Optional;

public interface InvoiceBuyingService {
    List<InvoiceBuyingDto> getInvoicesBuying();

    InvoiceBuyingDto saveInvoiceBuying(InvoiceBuyingDto invoiceBuying);

    InvoiceBuyingDto findById(int id);
}
