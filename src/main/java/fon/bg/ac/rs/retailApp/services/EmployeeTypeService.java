package fon.bg.ac.rs.retailApp.services;

import fon.bg.ac.rs.retailApp.dtos.EmployeeTypeDto;
import fon.bg.ac.rs.retailApp.models.EmployeeType;

import java.util.List;
import java.util.Optional;

public interface EmployeeTypeService {
    List<EmployeeTypeDto> getEmployeeTypes();

    EmployeeTypeDto saveEmployeeType(EmployeeTypeDto employeeType);

    EmployeeTypeDto findById(int id);

    void deleteById(int id);
}
