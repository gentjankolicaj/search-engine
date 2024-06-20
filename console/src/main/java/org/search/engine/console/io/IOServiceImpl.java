package org.search.engine.console.io;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.util.Properties;
import org.search.engine.console.command.Command;
import org.search.engine.console.command.CommandParser;
import org.search.engine.console.command.CommandParserImpl;
import org.search.engine.console.exception.CommandParseException;
import org.search.engine.console.exception.IndexParseException;
import org.search.engine.console.exception.QueryParseException;
import org.search.engine.console.message.Message;
import org.search.engine.console.reader.ConsoleReader;
import org.search.engine.console.reader.Reader;
import org.search.engine.console.util.JsonUtil;
import org.search.engine.console.util.ResourceUtil;
import org.search.engine.console.writer.ConsoleWriter;
import org.search.engine.console.writer.Writer;


public class IOServiceImpl implements IOService {

  private final Reader reader;
  private final Writer writer;
  private final CommandParser commandParser;
  private final Connection connection;

  public IOServiceImpl(Connection connection) {
    this.connection = connection;
    this.commandParser = new CommandParserImpl();
    this.reader = new ConsoleReader(commandParser);
    this.writer = new ConsoleWriter(commandParser);
  }


  @Override
  public void run() throws Exception {
    this.writer.write("### Connected to RabbitMQ message broker.");
    Thread publisherThread = new PublisherThread();
    Thread consumerThread = new ConsumerThread();
    publisherThread.start();
    consumerThread.start();
    this.writer.write("### Publisher & Consumer thread started. To exit press CTRL+C ");
    this.writer.write(
        "### To index type : index index_value token1 token2 .Ex : index 2222 orange apple ");
    this.writer.write(
        "### To search type : query token or query (token1 '&' or '|' token2). Ex: query orange or query (apple | orange)");
  }

  @Override
  public void stop() throws Exception {
    this.reader.close();
    this.writer.close();
  }

  class PublisherThread extends Thread {

    private final Properties properties = ResourceUtil.getApplicationProperties();

    public void run() {
      String queueName = properties.getProperty("message.broker.queue.produce");
      String exchange = properties.getProperty("message.broker.exchange");
      String routingKey = properties.getProperty("message.broker.routing-key");
      try {
        Channel channel;
        synchronized (connection) {
          channel = connection.createChannel();
        }
        channel.addShutdownListener(cause -> {
          cause.printStackTrace();
        });

        channel.exchangeDeclare(exchange, BuiltinExchangeType.TOPIC);
        channel.queueDeclare(queueName, false, false, false, null);
        channel.queueBind(queueName, exchange, routingKey);
        while (true) {
          try {
            Command command = reader.read();
            Message<Object, Command> message = new Message<>(null, command);
            String json = JsonUtil.toJson(message);
            channel.basicPublish(exchange, routingKey, null, json.getBytes());
          } catch (QueryParseException qpe) {
            writer.write("query error '" + qpe.getMessage() + "'");
          } catch (IndexParseException ipe) {
            writer.write("index error '" + ipe.getMessage() + "'");
          } catch (CommandParseException cpe) {
            writer.write("Command parse exception.'" + cpe.getMessage());
          } catch (Exception e) {
            writer.write("Unexpected exception " + e.getMessage() + ".Proceeding to shutdown...");
            break;
          }
        }
        channel.close();
        System.exit(-1);
      } catch (Exception e) {
        e.printStackTrace();
      }

    }
  }

  class ConsumerThread extends Thread {

    private final Properties properties = ResourceUtil.getApplicationProperties();

    public void run() {
      String queueName = properties.getProperty("message.broker.queue.consume");
      Channel channel = null;
      try {
        synchronized (connection) {
          channel = connection.createChannel();
        }
        channel.addShutdownListener(cause -> {
          cause.printStackTrace();
        });
        channel.queueDeclare(queueName, false, false, false, null);

        Consumer consumer = new DefaultConsumer(channel) {
          @Override
          public void handleDelivery(String consumerTag, Envelope envelope,
              AMQP.BasicProperties properties, byte[] body) {
            try {
              writer.write(body);
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        };
        channel.basicConsume(queueName, true, consumer);
      } catch (Exception e) {
        e.printStackTrace();

      }
    }
  }

}
