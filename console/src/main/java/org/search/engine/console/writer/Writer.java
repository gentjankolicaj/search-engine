package org.search.engine.console.writer;

import java.io.IOException;
import org.search.engine.console.command.Command;
import org.search.engine.console.message.Message;
import org.search.engine.console.result.Result;

public interface Writer {

  void write(Command input) throws IOException;

  void write(String input) throws IOException;

  void write(Message<Object, Result> message) throws IOException;

  void write(byte[] input) throws IOException;

  void close() throws IOException;
}
