package fon.bg.ac.rs.retailApp.servicesImpl;


import fon.bg.ac.rs.retailApp.dtos.ClientDto;
import fon.bg.ac.rs.retailApp.models.Client;
import fon.bg.ac.rs.retailApp.repositories.ClientRepository;
import fon.bg.ac.rs.retailApp.services.ClientService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    public ClientRepository clientRepository;

    @Override
    public List<ClientDto> getClients() {

        List<Client> all = clientRepository.findAll();
        List<ClientDto> dtos = all.stream()
                .map(d -> new ClientDto(d.getId(),
                        d.getFullName(),
                        d.getPhone(),
                        d.getEmail(),
                        d.getDetails(),
                        d.getLocation(),
                        d.getLocation().getId())).collect(Collectors.toList());

        return dtos;
        //iz baze mi se vraca Client model koji ja konvertujem i saljem kao data transfer object ka kontroleru
        //BITNO baza radi sa Client iz modela a pogled radi sa ClientDto iz data transfer objekta
    }

    @Override
    public ClientDto saveClient(ClientDto client) {


        Client d = new Client();
        BeanUtils.copyProperties(client, d);

        Client saved = clientRepository.save(d);
        client.setId(saved.getId());

        return client;
    }

    @Override
    public ClientDto findById(int id) {

        Client find=clientRepository.findById(id).get();
        ClientDto d= new ClientDto();
        BeanUtils.copyProperties(find, d);

        return d;
    }

    @Override
    public void deleteById(int id) {
        clientRepository.deleteById(id);
    }
}
