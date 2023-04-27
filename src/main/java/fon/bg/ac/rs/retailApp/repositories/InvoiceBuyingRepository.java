package fon.bg.ac.rs.retailApp.repositories;

import fon.bg.ac.rs.retailApp.models.InvoiceBuying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceBuyingRepository extends JpaRepository<InvoiceBuying, Integer> {
}
