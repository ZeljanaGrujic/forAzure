package fon.bg.ac.rs.retailApp.servicesImpl;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClientServiceImplTests {


    @Autowired
    private ClientServiceImpl clientService;

    @MockBean
    private ClientRepository clientRepository;

   /* @InjectMocks
    private ClientServiceImpl clientService;


    */

    private final Client client = new Client(1, "John Doe", "123456789", "john.doe@example.com", "Some details", new Location(1, "City 1", "Addres 1", "Details", new Country(1, "Code 1", "Name 1", "Capital 1", null),1), 1);

    private final ClientDto clientDto = new ClientDto(1, "John Doe", "123456789", "john.doe@example.com", "Some details", new Location(1, "City 1", "Addres 1", "Details", new Country(1, "Code 1", "Name 1", "Capital 1", null),1), 1);

    /*
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

     */

    @Test
    void testGetClients() {
        List<Client> clients = Arrays.asList(client);
        when(clientRepository.findAll()).thenReturn(clients);

        List<ClientDto> expected = Arrays.asList(clientDto);
        List<ClientDto> actual = clientService.getClients();

        assertEquals(expected, actual);
    }

    @Test
    void testSaveClient() {
        when(clientRepository.save(client)).thenReturn(client);

        ClientDto expected = clientDto;
        ClientDto actual = clientService.saveClient(clientDto);

        assertEquals(expected, actual);
    }

    @Test
    void testFindById() {
        when(clientRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(client));

        ClientDto expected = clientDto;
        ClientDto actual = clientService.findById(1);

        assertEquals(expected, actual);
    }

    @Test
    void testDeleteById() {
        clientService.deleteById(1);
    }
}
