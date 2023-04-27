package fon.bg.ac.rs.retailApp.repositories;

import fon.bg.ac.rs.retailApp.controllers.SupplierController;
import fon.bg.ac.rs.retailApp.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
