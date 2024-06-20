package org.search.engine.relational.builder.index;

import lombok.Data;

@Data
public class IndexSql<O> {

  private O query;
  private Object[] args;

  public IndexSql(O query, Object... args) {
    this.query = query;
    this.args = args;
  }
}
