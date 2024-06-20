package org.search.engine.relational.dao;

import java.util.List;
import java.util.Map;
import org.search.engine.relational.builder.index.IndexSql;
import org.search.engine.relational.builder.query.QuerySql;
import org.search.engine.relational.domain.DocumentToken;

public interface DocumentTokenDao {

  List<DocumentToken> readAll();

  DocumentToken read(String value);

  void create(DocumentToken documentToken);

  void delete(DocumentToken documentToken);

  List<DocumentToken> readAll(String sql);

  List<DocumentToken> readAll(QuerySql query);

  int create(List<String> sqlList);

  void update(Map<String, IndexSql> map);
}
