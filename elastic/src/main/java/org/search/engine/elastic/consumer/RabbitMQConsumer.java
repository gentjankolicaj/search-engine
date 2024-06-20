package org.search.engine.elastic.consumer;

import lombok.extern.slf4j.Slf4j;
import org.search.engine.elastic.dto.Message;
import org.search.engine.elastic.dto.command.ConsoleCommand;
import org.search.engine.elastic.service.DocumentService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class RabbitMQConsumer implements Consumer {

  private static final String CONSUME_QUEUE = "command";

  private final DocumentService documentService;
  private final TaskExecutor taskExecutor;

  @Autowired
  public RabbitMQConsumer(DocumentService documentService, TaskExecutor taskExecutor) {
    this.documentService = documentService;
    this.taskExecutor = taskExecutor;
  }

  @RabbitListener(queues = CONSUME_QUEUE, concurrency = "3")
  public void consume(Message<Object, ConsoleCommand> message) {
    ConsoleCommand consoleCommand = message.getBody();
    log.info("Command received " + consoleCommand);
    if (consoleCommand != null) {
      if (consoleCommand.getType() == 1) {
        log.info("Command " + consoleCommand + " , indexing with index-service.");
        taskExecutor.execute(() -> documentService.index(consoleCommand));
        log.info("Command indexing  " + consoleCommand + "  placed on thread pool.");
      } else if (consoleCommand.getType() == 2) {
        log.info("Command " + consoleCommand + " , querying with query-service.");
        taskExecutor.execute(() -> documentService.query(consoleCommand));
        log.info("Command querying " + consoleCommand + "  placed on thread pool.");
      }
    } else {
      log.info("Command received " + consoleCommand);
    }
  }
}
