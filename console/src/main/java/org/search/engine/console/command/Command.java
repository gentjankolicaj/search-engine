package org.search.engine.console.command;

import java.io.Serializable;
import java.util.List;

public interface Command extends Serializable {

  int getType();

  void setType(int type);

  List<String> getParams();

  boolean addParam(String param);

  boolean removeParam(String param);

}
