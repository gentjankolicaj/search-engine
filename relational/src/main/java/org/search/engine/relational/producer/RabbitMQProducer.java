package org.search.engine.relational.producer;

import lombok.extern.slf4j.Slf4j;
import org.search.engine.relational.dto.Message;
import org.search.engine.relational.dto.result.Result;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class RabbitMQProducer implements Producer {

  private static final String PRODUCE_QUEUE = "result";
  private final AmqpTemplate amqpTemplate;

  @Autowired
  public RabbitMQProducer(AmqpTemplate amqpTemplate) {
    this.amqpTemplate = amqpTemplate;
  }

  public void produce(Result result) {
    amqpTemplate.convertAndSend(PRODUCE_QUEUE, new Message<>(null, result));
    log.info("Pushed to queue " + PRODUCE_QUEUE + " : " + result);
  }
}
