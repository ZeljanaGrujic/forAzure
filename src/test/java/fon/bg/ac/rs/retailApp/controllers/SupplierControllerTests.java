package fon.bg.ac.rs.retailApp.controllers;

import fon.bg.ac.rs.retailApp.dtos.LocationDto;
import fon.bg.ac.rs.retailApp.dtos.SupplierDto;
import fon.bg.ac.rs.retailApp.models.Location;
import fon.bg.ac.rs.retailApp.servicesImpl.LocationServiceImpl;
import fon.bg.ac.rs.retailApp.servicesImpl.SupplierServiceImpl;
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

class SupplierControllerTests {

    @Mock
    private SupplierServiceImpl supplierService;

    @Mock
    private LocationServiceImpl locationService;

    @Mock
    private Model model;

    @InjectMocks
    private SupplierController supplierController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetSuppliers() {
        List<LocationDto> locations = new ArrayList<>();
        List<SupplierDto> suppliers = new ArrayList<>();
        SupplierDto supplierDto = new SupplierDto();
        supplierDto.setFullName("Dobavljac1");
        supplierDto.setCompanyName("Kompanija1");
        supplierDto.setPhone("Telefon1");
        supplierDto.setEmail("Zaposleni1@gmail.com");
        supplierDto.setLocation(new Location());
        suppliers.add(supplierDto);

        when(locationService.getLocations()).thenReturn(locations);
        when(supplierService.getSuppliers()).thenReturn(suppliers);

        String viewName = supplierController.getSuppliers(model);

        assertEquals("Supplier", viewName);
        verify(model, times(1)).addAttribute(eq("locations"), eq(locations));
        verify(model, times(1)).addAttribute(eq("suppliers"), eq(suppliers));
    }

    @Test
    void testAddNew() {
        SupplierDto supplierDto = new SupplierDto();
        supplierDto.setFullName("Dobavljac1");
        supplierDto.setCompanyName("Kompanija1");
        supplierDto.setPhone("Telefon1");
        supplierDto.setEmail("Zaposleni1@gmail.com");
        supplierDto.setLocation(new Location());
        when(supplierService.saveSupplier(supplierDto)).thenReturn(supplierDto);

        String viewName = supplierController.addBew(supplierDto);

        assertEquals("redirect:/suppliers", viewName);
        verify(supplierService, times(1)).saveSupplier(supplierDto);
    }

    @Test
    void testFindById() {
        int id = 1;
        SupplierDto supplierDto = new SupplierDto();
        supplierDto.setFullName("Dobavljac1");
        supplierDto.setCompanyName("Kompanija1");
        supplierDto.setPhone("Telefon1");
        supplierDto.setEmail("Zaposleni1@gmail.com");
        supplierDto.setLocation(new Location());
        when(supplierService.findById(id)).thenReturn(supplierDto);

        SupplierDto result = supplierController.findById(id);

        assertEquals(supplierDto, result);
        verify(supplierService, times(2)).findById(id);
    }

    @Test
    void testUpdate() {
        SupplierDto supplierDto = new SupplierDto();
        supplierDto.setFullName("Dobavljac1");
        supplierDto.setCompanyName("Kompanija1");
        supplierDto.setPhone("Telefon1");
        supplierDto.setEmail("Zaposleni1@gmail.com");
        supplierDto.setLocation(new Location());
        when(supplierService.saveSupplier(supplierDto)).thenReturn(supplierDto);

        String viewName = supplierController.update(supplierDto);

        assertEquals("redirect:/suppliers", viewName);
        verify(supplierService, times(1)).saveSupplier(supplierDto);
    }

    @Test
    void testDeleteById() {
        int id = 1;

        String viewName = supplierController.deleteById(id);

        assertEquals("redirect:/suppliers", viewName);
        verify(supplierService, times(1)).deleteById(id);
    }
}