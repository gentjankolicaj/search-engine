package org.search.engine.relational.dao;

import org.search.engine.relational.domain.Token;

import java.util.List;

public interface TokenDao {

    List<Token> readAll();

    Token read(String value);

    void create(Token token);

    void delete(String value);
}
