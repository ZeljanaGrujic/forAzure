package fon.bg.ac.rs.retailApp.servicesImpl;

import fon.bg.ac.rs.retailApp.dtos.InvoiceBItemDto;
import fon.bg.ac.rs.retailApp.models.InvoiceBItem;
import fon.bg.ac.rs.retailApp.repositories.InvoiceBItemRepository;
import fon.bg.ac.rs.retailApp.services.InvoiceBItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceBItemServiceImpl implements InvoiceBItemService {

    @Autowired
    private InvoiceBItemRepository invoiceBItemRepository;

    @Override
    public InvoiceBItemDto saveInvoiceItem(InvoiceBItemDto invoiceItem) {


        InvoiceBItem d = new InvoiceBItem();
        BeanUtils.copyProperties(invoiceItem, d);
        InvoiceBItem saved = invoiceBItemRepository.save(d);
        invoiceItem.setId(saved.getId());

        return invoiceItem;
    }

    @Override
    public List<InvoiceBItem> findByInvoiceBuyingId(int id) {
        return invoiceBItemRepository.findByInvoiceBuyingId(id);
    }

    @Override
    public InvoiceBItemDto findById(int id) {

        InvoiceBItem find = invoiceBItemRepository.findById(id).get();
        InvoiceBItemDto d= new InvoiceBItemDto();
        BeanUtils.copyProperties(find, d);

        return d;
    }

    @Override
    public void deleteById(int id) {

        invoiceBItemRepository.deleteById(id);
    }



//    @Override
//    public void deleteByTextileId(int invoicesellingid, int textileid) {
//        invoiceItemRepository.deleteByTextileId(invoicesellingid, textileid);
//    }


    //    @Override
//    public List<LocationDto> findByCountryid(int id) {
//        return locationRepository.findByCountryid(id);
//    }
}
