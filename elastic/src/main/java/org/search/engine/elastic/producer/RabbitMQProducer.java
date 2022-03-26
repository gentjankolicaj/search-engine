package org.search.engine.elastic.producer;

import lombok.extern.slf4j.Slf4j;
import org.search.engine.elastic.dto.Message;
import org.search.engine.elastic.dto.result.Result;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class RabbitMQProducer {
    private static final String produceQueue="result";
    private final AmqpTemplate amqpTemplate;

    @Autowired
    public RabbitMQProducer(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public void produce(Result result) {
        amqpTemplate.convertAndSend(produceQueue, new Message<>(null, result));
        log.info("Pushed to queue " + produceQueue + " : " + result);
    }
}
