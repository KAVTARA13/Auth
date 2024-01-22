package com.example.oauth2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "Product", timeToLive = 20)
public class Product implements Serializable {
    @Id
    private Integer id;
    private String name;
    private int qty;
    private float price;
}
