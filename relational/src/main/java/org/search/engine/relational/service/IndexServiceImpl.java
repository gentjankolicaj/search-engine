package org.search.engine.relational.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.search.engine.relational.builder.index.IndexBuilder;
import org.search.engine.relational.builder.index.IndexSql;
import org.search.engine.relational.dao.DocumentTokenDao;
import org.search.engine.relational.dto.command.Command;
import org.search.engine.relational.dto.result.IndexResult;
import org.search.engine.relational.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class IndexServiceImpl implements IndexService {

  private final Producer producer;
  private final DocumentTokenDao documentTokenDao;
  private final IndexBuilder indexBuilder;


  @Autowired
  public IndexServiceImpl(Producer producer, DocumentTokenDao documentTokenDao,
      IndexBuilder indexBuilder) {
    this.producer = producer;
    this.documentTokenDao = documentTokenDao;
    this.indexBuilder = indexBuilder;
  }

  @Override
  public void index(Command command) {
    log.info("Index called with command " + command);
    try {
      List<String> params = command.getParams();
      Map<String, IndexSql> queryMap = indexBuilder.setParams(params)
          .build();
      documentTokenDao.update(queryMap);
      List<Object> results = new ArrayList<>();
      results.add("ok");
      results.add(params.get(1));
      producer.produce(new IndexResult(1, results));
      log.info("Published to result queue : " + results);
    } catch (Exception e) {
      log.error(e.getMessage());
      List<Object> results = new ArrayList<>();
      results.add("error");
      results.add(e.getMessage());
      producer.produce(new IndexResult(-1, results));
      log.info("Published to result queue : " + results);
    }
  }

}
