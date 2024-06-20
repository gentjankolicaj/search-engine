package org.search.engine.console.message;

import com.rabbitmq.client.ConnectionFactory;
import java.util.Properties;
import org.search.engine.console.util.ResourceUtil;

public class MessageBrokerConnectionFactory {

  private static final Properties properties = ResourceUtil.getApplicationProperties();
  private static final ConnectionFactory instance;

  static {
    instance = new ConnectionFactory();
    instance.setHost(properties.getProperty("message.broker.host"));
    instance.setPort(Integer.parseInt(properties.getProperty("message.broker.port")));
    instance.setUsername(properties.getProperty("message.broker.username"));
    instance.setPassword(properties.getProperty("message.broker.password"));
    instance.setConnectionTimeout(
        Integer.parseInt(properties.getProperty("message.broker.conn.timeout")));
    instance.setAutomaticRecoveryEnabled(false);
  }


  private MessageBrokerConnectionFactory() {
  }

  public static ConnectionFactory getInstance() {
    return instance;
  }
}
