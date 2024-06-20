package org.search.engine.console.reader;

import java.io.IOException;
import org.search.engine.console.command.Command;

public interface Reader<O extends Command> {

  O read() throws RuntimeException, IOException;

  void close() throws IOException;

}
