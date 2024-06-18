package com.apboutos.moneytrackapi.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import static com.apboutos.moneytrackapi.notification.MessageQueueConfig.*;

@Component
@RequiredArgsConstructor
public class MessageSender {

    private final RabbitTemplate rabbitTemplate;


    public void sendMessage(String message){
        rabbitTemplate.convertAndSend(topicExchangeName, routingkey, message);
    }
}
