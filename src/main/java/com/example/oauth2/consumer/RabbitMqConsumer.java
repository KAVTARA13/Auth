package com.example.oauth2.consumer;

import com.example.oauth2.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqConsumer.class);
    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consume(UserInfo message){
        LOGGER.info("Receive message -> "+message.toString());
    }
}
