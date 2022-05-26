package com.example.redistest.repositories;

import com.example.redistest.entities.Client;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface ClientRepository extends CrudRepository<Client, UUID> {
}
