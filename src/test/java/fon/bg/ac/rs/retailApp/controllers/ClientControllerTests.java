package fon.bg.ac.rs.retailApp.controllers;

import fon.bg.ac.rs.retailApp.dtos.ClientDto;
import fon.bg.ac.rs.retailApp.dtos.LocationDto;
import fon.bg.ac.rs.retailApp.models.Country;
import fon.bg.ac.rs.retailApp.models.Location;
import fon.bg.ac.rs.retailApp.servicesImpl.ClientServiceImpl;
import fon.bg.ac.rs.retailApp.servicesImpl.LocationServiceImpl;
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

class ClientControllerTests {

    @Mock
    private ClientServiceImpl clientService;

    @Mock
    private LocationServiceImpl locationService;

    @Mock
    private Model model;

    @InjectMocks
    private ClientController clientController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetClients() {
        List<LocationDto> locations = new ArrayList<>();
        LocationDto ldto= new LocationDto(1, "Grad 1", "Adresa 1", "Detalji1", new Country(1, "Kod 1", "REgion 1", "Najveci grad 1", null),1);
        locations.add(ldto);
        when(locationService.getLocations()).thenReturn(locations);

        List<ClientDto> clients = new ArrayList<>();
        ClientDto clientDto = new ClientDto(1, "Klijent 1", "123456789", "klijent1@gmail.com", "Detalji1", new Location(1, "Grad 1", "Adresa 1", "Detalji1", new Country(1, "Kod 1", "Region 1", "Najveci grad 1", null),1), 1);

        clients.add(clientDto);
        when(clientService.getClients()).thenReturn(clients);

        String viewName = clientController.getClients(model);
        assertEquals("Client", viewName);
        verify(model, times(1)).addAttribute(eq("locations"), eq(locations));
        verify(model, times(1)).addAttribute(eq("clients"), eq(clients));
    }

    @Test
    void testAddBew() {
        ClientDto client = new ClientDto();
        when(clientService.saveClient(client)).thenReturn(client);

        String viewName = clientController.addBew(client);
        assertEquals("redirect:/clients", viewName);
        verify(clientService, times(1)).saveClient(client);
    }

    @Test
    void testFindById() {
        int id = 1;
        ClientDto client = new ClientDto();
        when(clientService.findById(id)).thenReturn(client);

        ClientDto result = clientController.findById(id);
        assertEquals(client, result);
        verify(clientService, times(2)).findById(id);
    }

    @Test
    void testUpdate() {
        ClientDto client = new ClientDto();
        when(clientService.saveClient(client)).thenReturn(client);

        String viewName = clientController.update(client);
        assertEquals("redirect:/clients", viewName);
        verify(clientService, times(1)).saveClient(client);
    }

    @Test
    void testDeleteById() {
        int id = 1;

        String viewName = clientController.deleteById(id);
        assertEquals("redirect:/clients", viewName);
        verify(clientService, times(1)).deleteById(id);
    }
}