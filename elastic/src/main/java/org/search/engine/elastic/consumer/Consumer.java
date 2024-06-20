package org.search.engine.elastic.consumer;

import org.search.engine.elastic.dto.Message;
import org.search.engine.elastic.dto.command.ConsoleCommand;

public interface Consumer {

  void consume(Message<Object, ConsoleCommand> message) throws RuntimeException;

}
