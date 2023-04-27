package fon.bg.ac.rs.retailApp.repositories;

import fon.bg.ac.rs.retailApp.models.InvoiceBItem;
import fon.bg.ac.rs.retailApp.models.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceBItemRepository extends JpaRepository<InvoiceBItem, Integer> {


    List<InvoiceBItem> findByInvoiceBuyingId(int id);

//    @Query(
//            value="DELETE * FROM invoice_item WHERE invoicesellingid= ?1 AND textileid= ?2",
//            nativeQuery = true
//
//    )
//    void deleteByTextileId(int invoicesellingid, int textileid);
}
