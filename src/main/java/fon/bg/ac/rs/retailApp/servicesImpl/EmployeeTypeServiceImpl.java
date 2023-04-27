package fon.bg.ac.rs.retailApp.servicesImpl;

import fon.bg.ac.rs.retailApp.dtos.EmployeeTypeDto;
import fon.bg.ac.rs.retailApp.models.EmployeeType;
import fon.bg.ac.rs.retailApp.repositories.EmployeeTypeRepository;
import fon.bg.ac.rs.retailApp.services.EmployeeTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeTypeServiceImpl implements EmployeeTypeService {

    @Autowired
    private EmployeeTypeRepository employeeTypeRepository;

    @Override
    public List<EmployeeTypeDto> getEmployeeTypes() {

        List<EmployeeType> all = employeeTypeRepository.findAll();
        List<EmployeeTypeDto> dtos = all.stream()
                .map(d -> new EmployeeTypeDto(d.getId(),
                        d.getDescription(),
                        d.getDetails())).collect(Collectors.toList());

        return dtos;
    }

    @Override
    public EmployeeTypeDto saveEmployeeType(EmployeeTypeDto employeeType) {
        EmployeeType d = new EmployeeType();
        BeanUtils.copyProperties(employeeType, d);

        EmployeeType saved = employeeTypeRepository.save(d);
        employeeType.setId(saved.getId());
        return employeeType;
    }

    @Override
    public EmployeeTypeDto findById(int id) {


        EmployeeType find=employeeTypeRepository.findById(id).get();
        EmployeeTypeDto d= new EmployeeTypeDto();
        BeanUtils.copyProperties(find, d);

        return d;
    }

    @Override
    public void deleteById(int id) {

        employeeTypeRepository.deleteById(id);
    }
}
