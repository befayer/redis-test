package com.example.redistest.entities;

import lombok.Data;
import javax.persistence.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.UUID;

@Data
@RedisHash("Bank")
public class Bank implements Serializable {
    @Id
    private String id;

    @Indexed
    private String bankName;

    @Indexed
    private String bankCity;
}
