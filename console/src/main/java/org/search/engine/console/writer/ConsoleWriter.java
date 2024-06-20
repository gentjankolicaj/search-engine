package org.search.engine.console.writer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import org.search.engine.console.command.Command;
import org.search.engine.console.command.CommandParser;
import org.search.engine.console.message.Message;
import org.search.engine.console.result.Result;
import org.search.engine.console.util.JsonUtil;
import org.search.engine.console.util.ResourceUtil;

public class ConsoleWriter implements Writer {

  private final BufferedWriter bufferedWriter;
  private final CommandParser commandParser;
  private final Properties properties;


  public ConsoleWriter(CommandParser commandParser) {
    this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    this.commandParser = commandParser;
    this.properties = ResourceUtil.getApplicationProperties();
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

  }

  @Override
  public void write(byte[] input) throws IOException {
    Message message = JsonUtil.fromJsonToObject(input);
    if (message != null) {
      Object body = message.getBody();
      if (body instanceof LinkedHashMap) {
        LinkedHashMap linkedHashMap = (LinkedHashMap) message.getBody();
        Integer type = linkedHashMap.get("type") != null ? (Integer) linkedHashMap.get("type") : 0;
        if (type == 1) {
          bufferedWriter.write("-> index " + linkedHashMap.get("results") + "\n");
        } else if (type == 2) {
          Object values = linkedHashMap.get("results");
          if (values instanceof List) {
            List<?> valuesList = (List) values;
            if (isId()) {
              List<Integer> documentIds = valuesList.stream().map(d -> {
                Integer docId = 0;
                if (d instanceof LinkedHashMap) {
                  Object value = ((LinkedHashMap) d).get("documentId");
                  if (value == null) {
                    value = ((LinkedHashMap) d).get("docId");
                  }
                  if (value != null) {
                    docId = (Integer) value;
                  }
                }
                return docId;
              }).distinct().collect(Collectors.toList());
              bufferedWriter.write("-> query result " + documentIds + "\n");
            } else if (valuesList.size() == 0) {
              bufferedWriter.write("-> query result \n");
            } else {
              bufferedWriter.write("-> query result " + linkedHashMap.get("results") + "\n");
            }
          } else {
            bufferedWriter.write("-> query result \n");
          }

        } else if (type == -1) {
          bufferedWriter.write("-> index error " + linkedHashMap.get("results") + "\n");
        } else if (type == -2) {
          bufferedWriter.write("-> query error " + linkedHashMap.get("results") + "\n");
        }

        bufferedWriter.flush();
      }
    }
  }

  @Override
  public void close() throws IOException {
    bufferedWriter.close();
  }

  private boolean isId() {
    String resultShow = properties.getProperty("query.result.show");
    return resultShow.contains("id");
  }
}
