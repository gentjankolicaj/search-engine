package org.search.engine.relational.dto.result;

import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IndexResult implements Result<Object> {

  private Integer type;
  private List<Object> results;

  @Override
  public Integer getType() {
    return type;
  }

  @Override
  public List<Object> getResults() {
    return results;
  }
}
