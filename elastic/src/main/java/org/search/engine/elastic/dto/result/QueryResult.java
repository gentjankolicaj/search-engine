package org.search.engine.elastic.dto.result;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
