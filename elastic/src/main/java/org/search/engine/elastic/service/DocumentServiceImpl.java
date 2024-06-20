package org.search.engine.elastic.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.search.engine.elastic.dao.DocumentDao;
import org.search.engine.elastic.domain.Document;
import org.search.engine.elastic.dto.command.Command;
import org.search.engine.elastic.dto.result.IndexResult;
import org.search.engine.elastic.dto.result.QueryResult;
import org.search.engine.elastic.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DocumentServiceImpl implements DocumentService {

  private final DocumentDao documentDao;
  private final Producer producer;

  @Autowired
  public DocumentServiceImpl(DocumentDao documentDao, Producer producer) {
    this.documentDao = documentDao;
    this.producer = producer;
  }


  @Override
  public void index(Command command) {
    log.info("Index called with command " + command);
    try {
      List<String> params = command.getParams();
      Document document = getDocument(params);
      documentDao.update(document);
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


  @Override
  public void query(Command command) {
    log.info("Query with command " + command + " executed.");
    try {
      List<String> params = command.getParams();
      String query = getQueryStr(params);
      List<Document> documents = documentDao.readAll(query);
      producer.produce(new QueryResult<>(2, documents));
      log.info("Published to result queue : " + documents);
    } catch (Exception e) {
      log.error(e.getMessage());
      producer.produce(new QueryResult<>(-2, Collections.singletonList(e.getMessage())));
      log.info("Published to result queue : " + e.getMessage());
    }
  }

  private String getQueryStr(List<String> params) {
    List<String> tokens = params.subList(1, params.size());
    String rawTokens = String.join("", tokens);
    //replace parenthesis
    rawTokens = rawTokens.replace("(", "").replace(")", "");
    //replace & or |
    rawTokens = rawTokens.replace("&", " AND ").replace("|", " OR ");
    return rawTokens;
  }

  private Document getDocument(List<String> params) {
    Long docId = Long.valueOf(params.get(1));
    List<String> tokens = params.subList(2, params.size());
    return new Document(docId, tokens);
  }

}
