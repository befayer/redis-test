package com.example.redistest.repositories;

import com.example.redistest.entities.Currency;
import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface CurrencyRepository extends CrudRepository<Currency, UUID> {
}
