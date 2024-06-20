package org.search.engine.relational.builder.query;

import lombok.Data;

@Data
public class QuerySql<O> {

  private O query;
  private Object[] args;

  public QuerySql(O query, Object... args) {
    this.query = query;
    this.args = args;
  }
}
