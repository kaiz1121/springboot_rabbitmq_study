package com.kaiz.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Sender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${mq.config.exchange}")
    private String exchange;

    public void send() throws InterruptedException {
        int i=0;
        while (i<10){
            Thread.sleep(1000);
            String msg = "hello:" + new Date();
            this.amqpTemplate.convertAndSend(this.exchange,"log.error.routing.key",msg);
            i++;
        }
        String msg = "hello:" + new Date()+" "+"现在i超过10了，现在是Info log";
        this.amqpTemplate.convertAndSend(this.exchange,"log.info.routing.key",msg);

    }
}
