package fon.bg.ac.rs.retailApp.servicesImpl;

import fon.bg.ac.rs.retailApp.dtos.SupplierDto;
import fon.bg.ac.rs.retailApp.models.Supplier;
import fon.bg.ac.rs.retailApp.repositories.SupplierRepository;
import fon.bg.ac.rs.retailApp.services.SupplierService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;
    @Override
    public List<SupplierDto> getSuppliers() {

        List<Supplier> all = supplierRepository.findAll();
        List<SupplierDto> dtos = all.stream()
                .map(d -> new SupplierDto(d.getId(),
                        d.getFullName(),
                        d.getPhone(),
                        d.getEmail(),
                        d.getCompanyName(),
                        d.getDetails(),
                        d.getLocation(),
                        d.getLocation().getId())).collect(Collectors.toList());

        return dtos;
    }

    @Override
    public SupplierDto saveSupplier(SupplierDto supplier) {


        Supplier d = new Supplier();
        BeanUtils.copyProperties(supplier, d);

        Supplier saved = supplierRepository.save(d);
        supplier.setId(saved.getId());

        return supplier;
    }

    @Override
    public SupplierDto findById(int id) {

        Supplier find=supplierRepository.findById(id).get();
        SupplierDto d= new SupplierDto();
        BeanUtils.copyProperties(find, d);

        return d;
    }

    @Override
    public void deleteById(int id) {
        supplierRepository.deleteById(id);
    }
}
