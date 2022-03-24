package org.search.engine.console.writer;

import org.search.engine.console.command.Command;
import org.search.engine.console.command.CommandParser;
import org.search.engine.console.message.Message;
import org.search.engine.console.result.Result;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedHashMap;

public class ConsoleWriter implements Writer {
    private final BufferedWriter bufferedWriter;
    private final CommandParser commandParser;

    public ConsoleWriter(BufferedWriter bufferedWriter, CommandParser commandParser) {
        this.bufferedWriter = bufferedWriter;
        this.commandParser = commandParser;
    }

    public ConsoleWriter(CommandParser commandParser) {
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        this.commandParser = commandParser;
    }

    @Override
    public void write(Command input) throws IOException {
        if (input.getType() == 1) {
            bufferedWriter.write("<-[x] index ok " + input.getParams().get(1) + "\n");
            bufferedWriter.flush();
        }
    }

    @Override
    public void write(String input) throws IOException {
        bufferedWriter.write(input + "\n");
        bufferedWriter.flush();
    }

    @Override
    public void write(Message<Object, Result> message) throws IOException {
        Object body = message.getBody();
        if (body == null)
            return;
        if (body instanceof LinkedHashMap) {
            LinkedHashMap linkedHashMap = (LinkedHashMap) body;
            Integer type = linkedHashMap.get("type") != null ? (Integer) linkedHashMap.get("type") : 0;

            if (type == 1)
                bufferedWriter.write("->[*] index " + linkedHashMap.get("results") + "\n");
            else if (type == 2)
                bufferedWriter.write("->[$] query result " + linkedHashMap.get("results") + "\n");

        }
        bufferedWriter.flush();
    }

    @Override
    public void close() throws IOException {
        bufferedWriter.close();
    }
}
