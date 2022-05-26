package com.example.redistest.entities;

import lombok.Data;
import javax.persistence.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.lang.ref.Cleaner;
import java.util.UUID;
import java.io.Serializable;

@Data
@RedisHash("Account")
public class Account implements Serializable {
    @Id
    private String id;

    @Indexed
    private double balance;

    @Reference
    private Bank bank;

    @Reference
    private Currency currency;

    @Reference
    private Client client;
}
