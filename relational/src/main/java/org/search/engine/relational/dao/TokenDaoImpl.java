package org.search.engine.relational.dao;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.search.engine.relational.domain.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class TokenDaoImpl implements TokenDao {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public TokenDaoImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Token> readAll() {
    return null;
  }

  @Override
  public Token read(String value) {
    return null;
  }

  @Override
  public void create(Token token) {

  }

  @Override
  public void delete(String value) {

  }
}
