package com.deividsantos.bdd.service;

import com.deividsantos.bdd.dto.Client;
import com.deividsantos.bdd.repository.ClientRepository;
import com.deividsantos.bdd.restClient.CountryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CountryClient countryClient;

    public List<Client> get() throws IOException {
        return clientRepository.findAll();
    }

    public Client get(Integer code) throws Exception {
        return clientRepository.find(code);
    }

    public void insert(Client client) throws IOException {
        String countryName = countryClient.getCountryByCode(client.getNationality()).getRestResponse().getResult().getName();
        client.setNationality(countryName);
        clientRepository.save(client);
    }
}
