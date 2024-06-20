package org.search.engine.console.reader;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.search.engine.console.command.Command;
import org.search.engine.console.command.CommandParser;
import org.search.engine.console.command.CommandParserImpl;
import org.search.engine.console.exception.CommandParseException;


@ExtendWith(MockitoExtension.class)
class ConsoleReaderTest {

  Reader<Command> reader;
  CommandParser commandParser;

  BufferedReader bufferedReader;


  @AfterAll
  static void tear() {
  }


  @BeforeEach
  void setUp() {
    commandParser = new CommandParserImpl();
    reader = new ConsoleReader(bufferedReader, commandParser);
  }

  @AfterEach
  void tearDown() {
  }

  //todo : to fully implement with mocking
  void read() throws IOException {

    Command command0 = reader.read();
    assertNotNull(command0, "Command read is null");

    Command command1 = reader.read();
    assertNotNull(command1, "Command read is null");

    Command command2 = reader.read();
    assertNotNull(command2, "Command read is null");

    Command command3 = reader.read();
    assertNotNull(command3, "Command read is null");

    CommandParseException cpe0 = assertThrows(CommandParseException.class, () -> reader.read());
    assertTrue(cpe0.getMessage().contains("Can't parse null or size=0 input"));

    CommandParseException cpe1 = assertThrows(CommandParseException.class, () -> reader.read());
    assertTrue(cpe1.getMessage().contains("Can't parse null or size=0 input"));
  }


}