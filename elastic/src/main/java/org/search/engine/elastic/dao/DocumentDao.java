package org.search.engine.elastic.dao;


import java.util.List;
import org.apache.lucene.queryparser.classic.ParseException;
import org.search.engine.elastic.domain.Document;

public interface DocumentDao {

  void update(Document document);

  List<Document> readAll(String query) throws ParseException;
}
