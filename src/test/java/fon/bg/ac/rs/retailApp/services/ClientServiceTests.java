package fon.bg.ac.rs.retailApp.services;

import fon.bg.ac.rs.retailApp.dtos.ClientDto;
import fon.bg.ac.rs.retailApp.models.Client;
import fon.bg.ac.rs.retailApp.models.Country;
import fon.bg.ac.rs.retailApp.models.Location;
import fon.bg.ac.rs.retailApp.repositories.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class ClientServiceTests {


    @Autowired
    private ClientService service;
    @MockBean
    private ClientRepository repository;



    private final Client newObj = new Client(1, "Klijent1", "123456789", "klijent1@gmail.com", "Detalji1", new Location(1, "Grad 1", "Adresa 1", "Detalji1", new Country(1, "Kod 1", "naziv 1", "Region 1", null),1), 1);

    private final ClientDto newObjDto = new ClientDto(1, "Klijent1", "123456789", "klijent1@gmail.com", "Detalji1", new Location(1, "Grad 1", "Adresa 1", "Detalji1", new Country(1, "Kod 1", "naziv 1", "Region 1", null),1), 1);

    /*
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

     */

    @Test
    void testGetClients() {
        List<Client> items = Arrays.asList(newObj);
        when(repository.findAll()).thenReturn(items);

        List<ClientDto> expectedItems = Arrays.asList(newObjDto);
        List<ClientDto> foundItems = service.getClients();

        assertEquals(expectedItems, foundItems);
    }

    @Test
    void testSaveClient() {
        //ovim redom simuliram da je entitet uspesno sacuvan u bazi
        when(repository.save(newObj)).thenReturn(newObj);

        ClientDto expected = newObjDto;
        //i sada mogu samo da testiram biznis logiku servisa, i da se fokusiram na to a ne i na rad repozitorijuma
        //apstrahovala sam sve ono sto je ispod jer sam to vec istestirala u repositori testovima
        ClientDto found = service.saveClient(newObjDto);

        assertEquals(expected, found);
    }

    @Test
    void testFindById() {
        when(repository.findById(1)).thenReturn(Optional.ofNullable(newObj));

        ClientDto expected = newObjDto;
        ClientDto found = service.findById(1);

        assertEquals(expected, found);
    }

    @Test
    void testDeleteById() {
        when(repository.findById(newObj.getId())).thenReturn(Optional.ofNullable(newObj));
        service.deleteById(newObj.getId());
        verify(repository, times(1)).deleteById(newObj.getId());
    }

}