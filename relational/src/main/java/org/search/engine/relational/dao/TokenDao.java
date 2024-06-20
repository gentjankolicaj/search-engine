package org.search.engine.relational.dao;

import java.util.List;
import org.search.engine.relational.domain.Token;

public interface TokenDao {

  List<Token> readAll();

  Token read(String value);

  void create(Token token);

  void delete(String value);
}
