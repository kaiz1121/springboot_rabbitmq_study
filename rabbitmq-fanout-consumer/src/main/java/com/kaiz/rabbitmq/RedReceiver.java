package com.kaiz.rabbitmq;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "${mq.config.queue.red}",autoDelete = "true"),
        exchange = @Exchange(value = "${mq.config.exchange}",type = ExchangeTypes.FANOUT)
//        key = "${mq.config.queue.info.routing.key}"
)
)
public class RedReceiver {
    @RabbitHandler
    public void process(String msg){
        System.out.println("为用户发红包:"+msg);
    }
}
