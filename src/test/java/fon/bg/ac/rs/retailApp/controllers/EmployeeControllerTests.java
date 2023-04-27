package fon.bg.ac.rs.retailApp.controllers;

import fon.bg.ac.rs.retailApp.dtos.CountryDto;
import fon.bg.ac.rs.retailApp.dtos.EmployeeTypeDto;
import fon.bg.ac.rs.retailApp.dtos.JobTitleDto;
import fon.bg.ac.rs.retailApp.dtos.LocationDto;
import fon.bg.ac.rs.retailApp.models.Employee;
import fon.bg.ac.rs.retailApp.servicesImpl.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class EmployeeControllerTests {

    @Mock
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeTypeServiceImpl employeeTypeService;

    @Mock
    private JobTitleServiceImpl jobTitleService;

    @Mock
    private LocationServiceImpl locationService;

    @Mock
    private CountryServiceImpl countryService;

    @Mock
    private Model model;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee());
        when(employeeService.getEmployees()).thenReturn(employees);

        List<EmployeeTypeDto> employeeTypes = new ArrayList<>();
        employeeTypes.add(new EmployeeTypeDto());
        when(employeeTypeService.getEmployeeTypes()).thenReturn(employeeTypes);

        List<JobTitleDto> jobTitles = new ArrayList<>();
        jobTitles.add(new JobTitleDto());
        when(jobTitleService.getJobTitles()).thenReturn(jobTitles);

        List<LocationDto> locations = new ArrayList<>();
        locations.add(new LocationDto());
        when(locationService.getLocations()).thenReturn(locations);

        List<CountryDto> countries = new ArrayList<>();
        countries.add(new CountryDto());
        when(countryService.getCountries()).thenReturn(countries);

        String viewName = employeeController.getEmployees(model);

        assertEquals("Employee", viewName);

        verify(employeeService, times(1)).getEmployees();
        verify(employeeTypeService, times(1)).getEmployeeTypes();
        verify(jobTitleService, times(1)).getJobTitles();
        verify(locationService, times(1)).getLocations();
        verify(countryService, times(1)).getCountries();
        verify(model, times(1)).addAttribute(eq("employees"), eq(employees));
        verify(model, times(1)).addAttribute(eq("employeeTypes"), eq(employeeTypes));
        verify(model, times(1)).addAttribute(eq("jobTitles"), eq(jobTitles));
        verify(model, times(1)).addAttribute(eq("locations"), eq(locations));
        verify(model, times(1)).addAttribute(eq("countries"), eq(countries));
    }

    @Test
    void testAddNew() {
        Employee employee = new Employee();
        when(employeeService.saveEmployee(any(Employee.class))).thenReturn(employee);

        String viewName = employeeController.addBew(employee);

        assertEquals("redirect:/employees", viewName);

        verify(employeeService, times(1)).saveEmployee(eq(employee));
    }

    @Test
    void testFindById() {
        Integer id = 1;
        Optional<Employee> employee = Optional.of(new Employee());
        when(employeeService.findById(id)).thenReturn(employee);

        Optional<Employee> result = employeeController.findById(id);

        assertEquals(employee, result);

        verify(employeeService, times(2)).findById(eq(id));
    }

    @Test
    void testUpdate() {
        Employee employee = new Employee();
        when(employeeService.saveEmployee(any(Employee.class))).thenReturn(employee);

        String viewName = employeeController.update(employee);

        assertEquals("redirect:/employees", viewName);
        verify(employeeService, times(1)).saveEmployee(employee);

    }

    @Test
    void testDeleteById() {
        int id = 1;

        String viewName = employeeController.deleteById(id);

        assertEquals("redirect:/employees", viewName);
        verify(employeeService, times(1)).deleteById(id);
    }
}