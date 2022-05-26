package com.example.redistest.entities;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Id;

@Data
@RedisHash("Currency")
public class Currency implements Serializable {
    @Id
    private UUID id;

    @Indexed
    private String nameCurrency;
}
