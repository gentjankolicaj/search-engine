package org.search.engine.elastic.dao;


import org.apache.lucene.queryparser.classic.ParseException;
import org.search.engine.elastic.domain.Document;

import java.util.List;

public interface DocumentDao {

    void update(Document document);

    List<Document> readAll(String query) throws ParseException;
}
