package org.search.engine.relational.dto.result;

import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QueryResult<O> implements Result<O> {

  private Integer type;
  private List<O> results;


  @Override
  public Integer getType() {
    return type;
  }

  @Override
  public List<O> getResults() {
    return results;
  }
}
