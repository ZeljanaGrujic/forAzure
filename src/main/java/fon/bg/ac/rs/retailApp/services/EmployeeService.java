package fon.bg.ac.rs.retailApp.services;

import fon.bg.ac.rs.retailApp.models.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getEmployees();

    Employee saveEmployee(Employee employee);

    Optional<Employee> findById(int id);

    void deleteById(int id);

    Employee findByUsername(String username);

    public void assignUsername(int id);
}
