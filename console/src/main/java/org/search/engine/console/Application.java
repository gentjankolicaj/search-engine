package org.search.engine.console;


import com.rabbitmq.client.Connection;
import org.search.engine.console.io.IOService;
import org.search.engine.console.io.IOServiceImpl;
import org.search.engine.console.message.MessageBroker;
import org.search.engine.console.message.MessageBrokerImpl;

public class Application {


  public static void main(String[] args) {
    MessageBroker messageBroker = null;
    IOService ioService = null;
    try {
      messageBroker = new MessageBrokerImpl();
      Connection connection = messageBroker.connect();
      ioService = new IOServiceImpl(connection);
      ioService.run();
    } catch (Exception e) {
      e.printStackTrace();
      try {
        if (messageBroker != null) {
          messageBroker.disconnect();
        }
      } catch (Exception e1) {
        e1.printStackTrace();
      }
      try {
        if (ioService != null) {
          ioService.stop();
        }
      } catch (Exception e2) {
        e2.printStackTrace();
      }
    }
  }
}
