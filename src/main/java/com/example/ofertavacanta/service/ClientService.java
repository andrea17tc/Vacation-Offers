package com.example.ofertavacanta.service;

import com.example.ofertavacanta.domain.Client;
import com.example.ofertavacanta.repository.ClientDBRepository;
import com.example.ofertavacanta.utils.Observable;
import com.example.ofertavacanta.utils.Observer;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientService  {
    private ClientDBRepository clientDBRepository;

    public ClientService(ClientDBRepository clientDBRepository) {
        this.clientDBRepository = clientDBRepository;
    }

    public Iterable<Client> findAll(){
        return clientDBRepository.findAll();
    }

    public Optional<Client> findOne(Long id){
        return clientDBRepository.findOne(id);
    }
}
