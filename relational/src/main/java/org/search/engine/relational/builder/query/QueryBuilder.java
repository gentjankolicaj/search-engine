package org.search.engine.relational.builder.query;

import java.util.ArrayList;
import java.util.List;
import org.search.engine.relational.builder.Builder;
import org.springframework.stereotype.Component;


@Component
public class QueryBuilder implements Builder<QuerySql> {

  private List<String> params;
  private String table;
  private String column;


  public QueryBuilder() {
  }


  public QueryBuilder setParams(List<String> params) {
    this.params = params;
    return this;
  }

  public QueryBuilder setParam(String param) {
    this.params.add(param);
    return this;
  }

  public QueryBuilder setTable(String table) {
    this.table = table;
    return this;
  }

  public QueryBuilder setColumn(String column) {
    this.column = column;
    return this;
  }


  //todo: to use StringBuilder instead of +
  public QuerySql build() {
    if (table == null) {
      table = "document_token";
    }
    if (column == null) {
      column = "token_value";
    }
    String prefixParenthesis = "(";
    String suffixParenthesis = ")";
    String and = "&";
    String or = "|";
    String sql = "select * from " + table + " where ";
    int totalParams = params.size();
    List<String> mixedParams = params.subList(1, totalParams);
    List<String> tokens = new ArrayList<>();
    int totalMixedParams = mixedParams.size();
    for (int i = 0; i < totalMixedParams; i++) {
      String s = mixedParams.get(i);
      if (s.equals(prefixParenthesis)) {
        sql = sql + prefixParenthesis + " ";
      } else if (s.equals(suffixParenthesis)) {
        sql = sql + suffixParenthesis + " ";
      } else if (s.equals(and)) {
        sql = sql + "and ";
      } else if (s.equals(or)) {
        sql = sql + "or ";
      } else {
        sql = sql + column + "=? ";
        tokens.add(s);
      }
    }

    return new QuerySql(sql, tokens.toArray());
  }

  //todo: to use StringBuilder instead of +
  public String buildSql() {
    if (table == null) {
      table = "document_token";
    }
    if (column == null) {
      column = "token_value";
    }
    String prefixParenthesis = "(";
    String suffixParenthesis = ")";
    String and = "&";
    String or = "|";
    String sql = "select * from " + table + " where ";
    int totalParams = params.size();
    List<String> tokens = params.subList(1, totalParams);
    int totalTokens = tokens.size();
    for (int i = 0; i < totalTokens; i++) {
      String s = tokens.get(i);
      if (s.equals(prefixParenthesis)) {
        sql = sql + prefixParenthesis + " ";
      } else if (s.equals(suffixParenthesis)) {
        sql = sql + suffixParenthesis + " ";
      } else if (s.equals(and)) {
        sql = sql + "and ";
      } else if (s.equals(or)) {
        sql = sql + "or ";
      } else {
        sql = sql + column + "='" + s + "' ";
      }
    }
    return sql;
  }

}
