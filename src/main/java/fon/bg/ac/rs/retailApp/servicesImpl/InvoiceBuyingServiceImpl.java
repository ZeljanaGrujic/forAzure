package fon.bg.ac.rs.retailApp.servicesImpl;

import fon.bg.ac.rs.retailApp.dtos.InvoiceBuyingDto;
import fon.bg.ac.rs.retailApp.models.InvoiceBuying;
import fon.bg.ac.rs.retailApp.repositories.InvoiceBuyingRepository;
import fon.bg.ac.rs.retailApp.services.InvoiceBuyingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceBuyingServiceImpl implements InvoiceBuyingService {

    @Autowired
    private InvoiceBuyingRepository invoiceBuyingRepository;

    @Override
    public List<InvoiceBuyingDto> getInvoicesBuying() {


        List<InvoiceBuying> all = invoiceBuyingRepository.findAll();
        List<InvoiceBuyingDto> dtos = all.stream()
                .map(d -> new InvoiceBuyingDto(d.getId(),
                        d.getInvoiceDate(),
                        d.getInvoiceStatus(),
                        d.getInvoiceStatus().getId(),
                        d.getSupplier(),
                        d.getSupplier().getId(),
                        d.getSpecialRemarks())).collect(Collectors.toList());

        return dtos;
    }

    @Override
    public InvoiceBuyingDto saveInvoiceBuying(InvoiceBuyingDto invoiceBuying) {

        InvoiceBuying d = new InvoiceBuying();
        BeanUtils.copyProperties(invoiceBuying, d);

        InvoiceBuying saved = invoiceBuyingRepository.save(d);
        invoiceBuying.setId(saved.getId());

        return invoiceBuying;
    }

    @Override
    public InvoiceBuyingDto findById(int id) {

        InvoiceBuying find=invoiceBuyingRepository.findById(id).get();
        InvoiceBuyingDto d= new InvoiceBuyingDto();
        BeanUtils.copyProperties(find, d);

        return d;
    }

}
