package org.search.engine.console.message;

import com.rabbitmq.client.Connection;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface MessageBroker {

  Connection connect() throws IOException, TimeoutException;

  void disconnect() throws IOException, TimeoutException;

  Connection getConnection();

}
