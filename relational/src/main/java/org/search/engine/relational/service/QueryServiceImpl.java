package org.search.engine.relational.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.search.engine.relational.builder.query.QueryBuilder;
import org.search.engine.relational.builder.query.QuerySql;
import org.search.engine.relational.dao.DocumentTokenDao;
import org.search.engine.relational.domain.DocumentToken;
import org.search.engine.relational.dto.command.Command;
import org.search.engine.relational.dto.result.QueryResult;
import org.search.engine.relational.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class QueryServiceImpl implements QueryService {

  private final Producer producer;
  private final DocumentTokenDao documentTokenDao;
  private final QueryBuilder queryBuilder;

  @Autowired
  public QueryServiceImpl(Producer producer, DocumentTokenDao documentTokenDao,
      QueryBuilder queryBuilder) {
    this.producer = producer;
    this.documentTokenDao = documentTokenDao;
    this.queryBuilder = queryBuilder;
  }

  @Override
  public void query(Command command) {
    log.info("Query with command " + command + " executed.");
    try {
      List<String> params = command.getParams();
      //Get sublist without token keyword
      QuerySql<String> querySql = queryBuilder.setParams(params)
          .build();
      //Get document tokens
      List<DocumentToken> documentTokens = documentTokenDao.readAll(querySql);
      producer.produce(new QueryResult<>(2, documentTokens));
      log.info("Published to result queue : " + documentTokens);
    } catch (Exception e) {
      log.error(e.getMessage());
      producer.produce(new QueryResult<>(-2, List.of(e.getMessage())));
      log.info("Published to result queue : " + e.getMessage());
    }
  }

}
