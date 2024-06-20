package org.search.engine.relational.dao;

import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.search.engine.relational.builder.index.IndexSql;
import org.search.engine.relational.builder.query.QuerySql;
import org.search.engine.relational.domain.DocumentToken;
import org.search.engine.relational.mapper.DocumentTokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class DocumentTokenDaoImpl implements DocumentTokenDao {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public DocumentTokenDaoImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<DocumentToken> readAll() {
    return null;
  }

  @Override
  public DocumentToken read(String value) {
    return null;
  }

  @Override
  public void create(DocumentToken documentToken) {
  }

  @Override
  public void delete(DocumentToken documentToken) {
  }

  @Override
  public List<DocumentToken> readAll(String sql) {
    return jdbcTemplate.query(sql, new DocumentTokenMapper());
  }

  @Override
  public List<DocumentToken> readAll(QuerySql query) {
    return jdbcTemplate.query((String) query.getQuery(), new DocumentTokenMapper(),
        query.getArgs());
  }

  @Override
  public int create(List<String> sqlList) {
    return 0;
  }

  @Override
  public void update(Map<String, IndexSql> map) {
    IndexSql<String> insertDoc = map.get("insert_document");
    IndexSql<List<String>> insertTokens = map.get("insert_token");
    IndexSql<String> deleteDocToken = map.get("delete_document_token");
    IndexSql<String> insertDocToken = map.get("insert_document_token");
    try {
      //Insert new doc_id into document table
      jdbcTemplate.update(insertDoc.getQuery(), insertDoc.getArgs());

      //Insert new tokens into token table & handle error if existing token present
      List<String> insertTokensSqls = insertTokens.getQuery();
      Object[] insertTokensArgs = insertTokens.getArgs();
      if (insertTokensSqls != null && insertTokensArgs != null) {
        for (int i = 0; i < insertTokensSqls.size(); i++) {
          try {
            jdbcTemplate.update(insertTokensSqls.get(i), insertTokensArgs[i]);
          } catch (Exception e1) {
            log.info(e1.getMessage());
          }
        }
      }
      //Insert new document tokens into document_tokens table
      jdbcTemplate.update(insertDocToken.getQuery(), insertDocToken.getArgs());

    } catch (Exception e) {
      log.error(e.getMessage());

      //Delete existing rows on document_token table with doc_id
      jdbcTemplate.update(deleteDocToken.getQuery(), deleteDocToken.getArgs());

      //Insert new tokens into token table & handle error if existing token present
      List<String> insertTokensSqls = insertTokens.getQuery();
      Object[] args = insertTokens.getArgs();
      if (insertTokensSqls != null && args != null && insertTokensSqls.size() == args.length) {
        for (int i = 0; i < insertTokensSqls.size(); i++) {
          try {
            jdbcTemplate.update(insertTokensSqls.get(i), args[i]);
          } catch (Exception e1) {
            log.info(e1.getMessage());
          }
        }
      }
      //Insert new document tokens into document_tokens table
      jdbcTemplate.update(insertDocToken.getQuery(), insertDocToken.getArgs());
    }
  }


}
