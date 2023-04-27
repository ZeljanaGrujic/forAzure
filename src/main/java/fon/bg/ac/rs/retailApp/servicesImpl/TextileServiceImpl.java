package fon.bg.ac.rs.retailApp.servicesImpl;

import fon.bg.ac.rs.retailApp.dtos.TextileDto;
import fon.bg.ac.rs.retailApp.models.Textile;
import fon.bg.ac.rs.retailApp.repositories.InvoiceSellingRepository;
import fon.bg.ac.rs.retailApp.repositories.TextileRepository;
import fon.bg.ac.rs.retailApp.services.TextileService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TextileServiceImpl implements TextileService {

    @Autowired
    private TextileRepository textileRepository;

    @Autowired
    private InvoiceSellingRepository invoiceSellingRepository;

    @Override
    public List<TextileDto> getTextiles() {

        List<Textile> all = textileRepository.findAll();
        List<TextileDto> dtos = all.stream()
                .map(d -> new TextileDto(d.getId(),
                        d.getUniqueCode(),
                        d.getPiecePrice(),
                        d.getSpecialDescription(),
                        d.getAvailableQuantity(),
                        d.getPhoto(),
                        d.getPurpose(),
                        d.getAcquisitionDate(),
                        d.getTextileType(),
                        d.getTextileType().getId(),
                        d.getTextleMake(),
                        d.getTextleMake().getId(),
                        d.getTextileModel(),
                        d.getTextileModel().getId(),
                        d.getTextileStatus(),
                        d.getTextileStatus().getId(),
                        d.getInCharge(),
                        d.getInCharge().getId(),
                        d.getSupplier(),
                        d.getSupplier().getId())).collect(Collectors.toList());

        return dtos;
    }

    @Override
    public TextileDto saveTextile(TextileDto textile) {

        Textile d = new Textile();
        BeanUtils.copyProperties(textile, d);

        Textile saved = textileRepository.save(d);
        textile.setId(saved.getId());
        return textile;
    }

    @Override
    public TextileDto findById(int id) {

        Textile find=textileRepository.findById(id).get();
        TextileDto d= new TextileDto();
        BeanUtils.copyProperties(find, d);

        return d;
    }

    @Override
    public void deleteById(int id) {
        textileRepository.deleteById(id);
    }

    @Override
    public List<TextileDto> findByPurpose(String purpose) {
        List<Textile> all = textileRepository.findByPurpose(purpose);
        List<TextileDto> dtos = all.stream()
                .map(d -> new TextileDto(d.getId(),
                        d.getUniqueCode(),
                        d.getPiecePrice(),
                        d.getSpecialDescription(),
                        d.getAvailableQuantity(),
                        d.getPhoto(),
                        d.getPurpose(),
                        d.getAcquisitionDate(),
                        d.getTextileType(),
                        d.getTextileType().getId(),
                        d.getTextleMake(),
                        d.getTextleMake().getId(),
                        d.getTextileModel(),
                        d.getTextileModel().getId(),
                        d.getTextileStatus(),
                        d.getTextileStatus().getId(),
                        d.getInCharge(),
                        d.getInCharge().getId(),
                        d.getSupplier(),
                        d.getSupplier().getId())).collect(Collectors.toList());

        return dtos;
    }



//    public void addTextileItem(Integer invoiceId, Integer textileId){
//
//        InvoiceSelling invoice=  invoiceSellingRepository.findById(invoiceId).orElse(null);
//        Textile item= textileRepository.findById(textileId).orElse(null);
//
////        TextileDto d= new TextileDto();
////        BeanUtils.copyProperties(item, d);
//
////        Set<Textile> invoiceItems=invoice.getItems();
//        invoiceItems.add(item);
//
//        invoice.setItems(invoiceItems);
//        invoiceSellingRepository.save(invoice);
//    }

//    public void removeTextileItem(Integer invoiceId, Integer textileId){
//        InvoiceSelling invoice=  invoiceSellingRepository.findById(invoiceId).orElse(null);
//        Set<Textile> invoiceItems=invoice.getItems();
//        invoiceItems.removeIf(x -> x.getId()==textileId);
//
//        invoice.setItems(invoiceItems);
//        invoiceSellingRepository.save(invoice);
//
//    }

//    public Set<Textile> getInvoiceItems(InvoiceSellingDto invoiceSelling){
//        return invoiceSelling.getItems();
//    }
//
//    public Set<Textile> getNotInvoiceItems(InvoiceSellingDto invoiceSelling){
//
//        return textileRepository.getNotInvoiceItems(invoiceSelling.getId());
//    }
}
