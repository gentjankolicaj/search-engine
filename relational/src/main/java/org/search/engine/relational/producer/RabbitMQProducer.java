package org.search.engine.relational.producer;

import lombok.extern.slf4j.Slf4j;
import org.search.engine.relational.dto.Message;
import org.search.engine.relational.dto.result.Result;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class RabbitMQProducer {

    private final AmqpTemplate amqpTemplate;

    @Value("${rabbitmq.queue.produce}")
    private String produceQueue;

    @Autowired
    public RabbitMQProducer(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void publish(Result result) {
        amqpTemplate.convertAndSend(produceQueue, new Message<>(null, result));
        log.info("Pushed to queue " + produceQueue + " : " + result);
    }
}
