package org.search.engine.elastic.dto.result;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
