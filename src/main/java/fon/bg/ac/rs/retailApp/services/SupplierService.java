package fon.bg.ac.rs.retailApp.services;

import fon.bg.ac.rs.retailApp.dtos.SupplierDto;
import fon.bg.ac.rs.retailApp.models.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierService {
    List<SupplierDto> getSuppliers();

    SupplierDto saveSupplier(SupplierDto supplier);

    SupplierDto findById(int id);

    void deleteById(int id);
}
