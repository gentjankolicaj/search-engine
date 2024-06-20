package org.search.engine.relational.dao;

import java.util.List;
import org.search.engine.relational.domain.Document;

public interface DocumentDao {

  List<Document> readAll();

  Document read(Long docId);

  void create(Document document);

  void delete(Long docId);

}
