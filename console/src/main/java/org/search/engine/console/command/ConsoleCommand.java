package org.search.engine.console.command;

import java.util.ArrayList;
import java.util.List;

public class ConsoleCommand implements Command {

  private final List<String> params;
  private int type;

  public ConsoleCommand() {
    this.params = new ArrayList<>();
  }

  public ConsoleCommand(int type) {
    this.type = type;
    this.params = new ArrayList<>();
  }

  public ConsoleCommand(int type, List<String> params) {
    this.type = type;
    if (params != null) {
      this.params = params;
    } else {
      this.params = new ArrayList<>();
    }
  }

  @Override
  public int getType() {
    return type;
  }

  @Override
  public void setType(int type) {
    this.type = type;
  }

  @Override
  public List<String> getParams() {
    return this.params;
  }

  @Override
  public boolean addParam(String param) {
    return this.params.add(param);
  }

  @Override
  public boolean removeParam(String param) {
    return this.params.remove(param);
  }

  @Override
  public String toString() {
    return "ConsoleCommand{" +
        "type=" + type +
        ", params=" + params +
        '}';
  }
}
