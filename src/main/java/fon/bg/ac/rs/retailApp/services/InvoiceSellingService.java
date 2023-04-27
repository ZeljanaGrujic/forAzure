package fon.bg.ac.rs.retailApp.services;

import fon.bg.ac.rs.retailApp.dtos.InvoiceSellingDto;
import fon.bg.ac.rs.retailApp.models.InvoiceSelling;

import java.util.List;
import java.util.Optional;

public interface InvoiceSellingService {
    List<InvoiceSellingDto> getInvoicesSelling();

    InvoiceSellingDto saveInvoiceSelling(InvoiceSellingDto invoiceSelling);

    InvoiceSellingDto findById(int id);
}
