package org.search.engine.console.message;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class MessageBrokerImpl implements MessageBroker {

  private final ConnectionFactory connectionFactory;
  private Connection connection;


  public MessageBrokerImpl() {
    this.connectionFactory = MessageBrokerConnectionFactory.getInstance();
  }


  public Connection connect() throws IOException, TimeoutException {
    if (connection == null) {
      this.connection = connectionFactory.newConnection();
      return this.connection;
    } else if (connection.isOpen()) {
      return this.connection;
    } else if (!connection.isOpen()) {
      this.connection = connectionFactory.newConnection();
      return this.connection;
    } else {
      throw new IOException("Connection with message broker not established.");
    }
  }

  @Override
  public void disconnect() throws IOException {
    if (connection != null && connection.isOpen()) {
      this.connection.close();
    } else {
      throw new IOException("Connection with message broker not closed.");
    }
  }

  @Override
  public Connection getConnection() {
    return connection;
  }


}
