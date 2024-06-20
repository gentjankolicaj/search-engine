package org.search.engine.console.result;

import java.io.Serializable;
import java.util.List;

public interface Result<O> extends Serializable {

  Integer getType();

  List<O> getResults();

}
