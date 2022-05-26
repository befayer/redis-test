package com.example.redistest.repositories;

import com.example.redistest.entities.Bank;
import org.springframework.data.repository.CrudRepository;

public interface BankRepository extends CrudRepository<Bank, String> {
}
