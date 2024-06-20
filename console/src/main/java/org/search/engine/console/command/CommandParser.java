package org.search.engine.console.command;


import org.search.engine.console.exception.CommandParseException;

public interface CommandParser {

  Command parse(String input, String regex) throws CommandParseException;

  Command parse(String input) throws CommandParseException;


}
