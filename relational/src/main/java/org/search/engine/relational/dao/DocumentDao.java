package org.search.engine.relational.dao;

import org.search.engine.relational.domain.Document;

import java.util.List;

public interface DocumentDao {

        List<Document> readAll();

        Document read(Long docId);

        void create(Document document);

        void delete(Long docId);

}
