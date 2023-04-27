package fon.bg.ac.rs.retailApp.services;

import fon.bg.ac.rs.retailApp.dtos.ClientDto;
import fon.bg.ac.rs.retailApp.models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<ClientDto> getClients();

    ClientDto saveClient(ClientDto client);

    ClientDto findById(int id);

    void deleteById(int id);
}
