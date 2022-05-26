package com.example.redistest.repositories;

import com.example.redistest.entities.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, String> {
}

