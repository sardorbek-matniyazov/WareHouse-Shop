package com.example.warehouse.service;

import com.example.warehouse.entity.Client;
import com.example.warehouse.payload.Result;
import com.example.warehouse.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository repo;


    public Result add(Client dto) {
        if (repo.existsByNumber(dto.getNumber()))
            return Result.builder()
                    .message("the number has already used")
                    .status(false)
                    .build();

        repo.save(
                Client.builder()
                        .name(dto.getName())
                        .number(dto.getNumber())
                        .build()
        );
        return Result.builder()
                .message("the client has successfully created")
                .status(false)
                .build();
    }

    public Result edit(Long id, Client dto) {
        if (!repo.existsById(id))
            return Result.builder()
                    .message("there isn't any Client")
                    .status(false)
                    .build();

        Optional<Client> byNumber = repo.findByNumber(dto.getNumber());
        if (byNumber.isPresent()){
            Client client = byNumber.get();
            if (!client.getNumber().equals(dto.getNumber()))
                return Result.builder()
                        .message("the number has already used")
                        .status(false)
                        .build();
        }

        dto.setId(id);
        repo.save(dto);
        return Result.builder()
                .message("the client has successfully edited")
                .status(false)
                .build();
    }

    public Result delete(Long id) {
        try {
            repo.deleteById(id);
        } catch (Exception e){
            e.printStackTrace();
            Client byId = repo.getById(id);
            repo.save(byId);
            return Result.builder()
                    .message("the product hasn't deleted, cause of client's history")
                    .status(true)
                    .build();
        }

        return Result.builder()
                .message("the client has successfully deleted")
                .status(true)
                .build();
    }
}
