package com.example.oauth2.publisher;

import com.example.oauth2.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;


@Service
public class RabbitMQProducer {
    @Value("${rabbitmq.routingKey.name}")
    private String routingKey;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(UserInfo message){
        LOGGER.info("Message sent -> "+message);
        rabbitTemplate.convertAndSend(exchange,routingKey,message);
    }
}
