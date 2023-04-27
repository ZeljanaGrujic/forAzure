package fon.bg.ac.rs.retailApp.services;

import fon.bg.ac.rs.retailApp.dtos.InvoiceStatusDto;
import fon.bg.ac.rs.retailApp.models.InvoiceStatus;

import java.util.List;
import java.util.Optional;

public interface InvoiceStatusService {
    List<InvoiceStatusDto> getInvoiceStatuses();

    InvoiceStatusDto saveInvoiceStatus(InvoiceStatusDto invoiceStatus);

    InvoiceStatusDto findById(int id);
}
