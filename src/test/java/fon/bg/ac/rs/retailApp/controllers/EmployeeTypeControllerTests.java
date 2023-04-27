package fon.bg.ac.rs.retailApp.controllers;

import fon.bg.ac.rs.retailApp.dtos.EmployeeTypeDto;
import fon.bg.ac.rs.retailApp.servicesImpl.EmployeeTypeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmployeeTypeControllerTests {

    @Mock
    private EmployeeTypeServiceImpl employeeTypeService;

    @Mock
    private Model model;

    @InjectMocks
    private EmployeeTypeController employeeTypeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetEmployeeTypes() {
        List<EmployeeTypeDto> employeeTypes = new ArrayList<>();
        EmployeeTypeDto employeeTypeDto= new EmployeeTypeDto();
        employeeTypeDto.setId(1);
        employeeTypeDto.setDetails("Detalji 1");
        employeeTypeDto.setDescription("Opis 1");
        employeeTypes.add(employeeTypeDto);
        when(employeeTypeService.getEmployeeTypes()).thenReturn(employeeTypes);

        String viewName = employeeTypeController.getEmployeeTypes(model);
        assertEquals("EmployeeType", viewName);
        verify(model, times(1)).addAttribute(eq("employeeTypes"), eq(employeeTypes));
    }

    @Test
    void testAddNew() {
        EmployeeTypeDto employeeType = new EmployeeTypeDto();
        when(employeeTypeService.saveEmployeeType(employeeType)).thenReturn(employeeType);

        String viewName = employeeTypeController.addBew(employeeType);
        assertEquals("redirect:/employeeTypes", viewName);
        verify(employeeTypeService, times(1)).saveEmployeeType(employeeType);
    }

    @Test
    void testFindById() {
        int id = 1;
        EmployeeTypeDto employeeType = new EmployeeTypeDto();
        when(employeeTypeService.findById(id)).thenReturn(employeeType);

        EmployeeTypeDto result = employeeTypeController.findById(id);
        assertEquals(employeeType, result);
        verify(employeeTypeService, times(2)).findById(id);
    }

    @Test
    void testUpdate() {
        EmployeeTypeDto employeeType = new EmployeeTypeDto();
        when(employeeTypeService.saveEmployeeType(employeeType)).thenReturn(employeeType);

        String viewName = employeeTypeController.update(employeeType);
        assertEquals("redirect:/employeeTypes", viewName);
        verify(employeeTypeService, times(1)).saveEmployeeType(employeeType);
    }

    @Test
    void testDeleteById() {
        int id = 1;

        String viewName = employeeTypeController.deleteById(id);
        assertEquals("redirect:/employeeTypes", viewName);
        verify(employeeTypeService, times(1)).deleteById(id);
    }
}