package org.search.engine.console.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.search.engine.console.command.Command;
import org.search.engine.console.command.CommandParser;
import org.search.engine.console.command.CommandParserImpl;

public class ConsoleReader implements Reader<Command> {

  private final BufferedReader bufferedReader;
  private final CommandParser commandParser;

  public ConsoleReader(BufferedReader bufferedReader, CommandParser commandParser) {
    this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    this.commandParser = new CommandParserImpl();
  }

  public ConsoleReader(CommandParser commandParser) {
    this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    this.commandParser = new CommandParserImpl();
  }

  @Override
  public Command read() throws RuntimeException, IOException {
    String newLine = bufferedReader.readLine();
    return commandParser.parse(newLine);

  }

  @Override
  public void close() throws IOException {
    if (bufferedReader != null) {
      bufferedReader.close();
    }

  }


}
