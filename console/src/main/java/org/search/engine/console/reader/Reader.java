package org.search.engine.console.reader;

import org.search.engine.console.command.Command;

import java.io.IOException;

public interface Reader<O extends Command> {

    O read() throws RuntimeException, IOException;

    void close() throws IOException;

}
