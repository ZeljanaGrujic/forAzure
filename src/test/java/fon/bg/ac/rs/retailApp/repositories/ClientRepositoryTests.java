package fon.bg.ac.rs.retailApp.repositories;

import fon.bg.ac.rs.retailApp.models.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClientRepositoryTests {

    //podesila sam da kad mi se izvrsavaju ovi testovi ne idu direktno do baze nego koriste h2 in memory bazu podataka
    @Autowired
    private ClientRepository clientRepository;

    @Test
    void testSaveClient() {
        Client client = new Client();
        client.setFullName("Klijent1");
        client.setPhone("123456789");
        client.setEmail("klijent1@gmail.com");
        client.setDetails("Detalji1");
        Client savedClient = clientRepository.save(client);
        assertNotNull(savedClient.getId());
    }

    @Test
    void testFindById() {
        Client client = new Client();
        client.setFullName("Klijent1");
        client.setPhone("123456789");
        client.setEmail("klijent1@gmail.com");
        client.setDetails("Detalji1");
        Client savedClient = clientRepository.save(client);

        Client foundClient = clientRepository.findById(savedClient.getId()).orElse(null);
        assertNotNull(foundClient);
        assertEquals(savedClient.getFullName(), foundClient.getFullName());
        assertEquals(savedClient.getPhone(), foundClient.getPhone());
        assertEquals(savedClient.getEmail(), foundClient.getEmail());
        assertEquals(savedClient.getDetails(), foundClient.getDetails());
    }

    @Test
    void testGetAllClients() {
        Client client1 = new Client();
        client1.setFullName("Klijent1");
        client1.setPhone("123456789");
        client1.setEmail("klijent1@gmail.com");
        client1.setDetails("Detalji1");
        clientRepository.save(client1);

        Client client2 = new Client();
        client2.setFullName("Klijent2");
        client2.setPhone("123456789");
        client2.setEmail("klijent2@gmail.com");
        client2.setDetails("Detalji2");
        clientRepository.save(client2);

        List<Client> clients = clientRepository.findAll();
        assertEquals(2, clients.size());
    }


    @Test
    void testDeleteClient() {
        Client client = new Client();
        client.setFullName("Klijent1");
        client.setPhone("123456789");
        client.setEmail("klijent1@gmail.com");
        client.setDetails("Detalji1");
        Client savedClient = clientRepository.save(client);

        clientRepository.deleteById(savedClient.getId());
        Optional<Client> deletedClient=clientRepository.findById(savedClient.getId());
        assertFalse(deletedClient.isPresent());


        //Client deletedClient = clientRepository.findById(savedClient.getId()).orElse(null);
       // assertEquals(null, deletedClient);

    }
}