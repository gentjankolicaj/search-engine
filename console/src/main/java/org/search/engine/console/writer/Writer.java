package org.search.engine.console.writer;

import org.search.engine.console.command.Command;
import org.search.engine.console.message.Message;
import org.search.engine.console.result.Result;

import java.io.IOException;

public interface Writer {
    void write(Command input) throws IOException;

    void write(String input) throws IOException;

    void write(Message<Object, Result> message) throws IOException;

    void close() throws IOException;
}
