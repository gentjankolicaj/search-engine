package org.search.engine.elastic.dto.result;

import java.io.Serializable;
import java.util.List;

public interface Result<O> extends Serializable {

  List<O> getResults();

  Integer getType();
}
