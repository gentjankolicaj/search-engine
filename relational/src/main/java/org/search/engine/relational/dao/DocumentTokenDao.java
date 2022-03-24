package org.search.engine.relational.dao;

import org.search.engine.relational.domain.DocumentToken;

import java.util.List;

public interface DocumentTokenDao {

    List<DocumentToken> readAll();

    DocumentToken read(String value);

    void create(DocumentToken documentToken);

    void delete(DocumentToken documentToken);
}
