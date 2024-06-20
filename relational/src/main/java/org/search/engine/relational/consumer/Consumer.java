package org.search.engine.relational.consumer;

import org.search.engine.relational.dto.Message;
import org.search.engine.relational.dto.command.ConsoleCommand;

public interface Consumer {

  void consume(Message<Object, ConsoleCommand> message);

}
