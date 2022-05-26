package com.example.redistest.entities;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Id;

@Data
@RedisHash("Client")
public class Client implements Serializable {
    @Id
    private UUID id;

    @Indexed
    private String firstName;

    @Indexed
    private String lastName;

}
