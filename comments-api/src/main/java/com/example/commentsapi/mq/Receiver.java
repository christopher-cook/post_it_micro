package com.example.commentsapi.mq;

import com.netflix.discovery.converters.Auto;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RabbitListener(queues = "deleteCommentsByPostId")
@Component
public class Receiver {

    @RabbitHandler
    public void receive(String message) {
        System.out.println(message);
    }

}
