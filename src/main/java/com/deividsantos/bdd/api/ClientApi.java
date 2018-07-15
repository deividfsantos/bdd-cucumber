package com.deividsantos.bdd.api;

import com.deividsantos.bdd.dto.Client;
import com.deividsantos.bdd.input.ClientInput;
import com.deividsantos.bdd.output.ClientOutput;
import com.deividsantos.bdd.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/clients")
public class ClientApi {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping
    public void insertClient(@RequestBody ClientInput clientInput) throws IOException {
        clientService.insert(objectMapper.convertValue(clientInput, Client.class));
    }

    @GetMapping
    public List<ClientOutput> getAllClients() throws IOException {
        return clientService.get().stream()
                .map(client -> objectMapper.convertValue(client, ClientOutput.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{code}")
    public ClientOutput getClient(@PathVariable("code") Integer code) throws Exception {
        try {
            return objectMapper.convertValue(clientService.get(code), ClientOutput.class);
        } catch (Exception e) {
            throw new Exception("No clients found with the code entered.");
        }
    }

}
