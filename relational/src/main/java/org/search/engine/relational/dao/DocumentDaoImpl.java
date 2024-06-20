package org.search.engine.relational.dao;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.search.engine.relational.domain.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DocumentDaoImpl implements DocumentDao {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public DocumentDaoImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Document> readAll() {
    return null;
  }

  @Override
  public Document read(Long docId) {
    return null;
  }

  @Override
  public void create(Document document) {
  }

  @Override
  public void delete(Long docId) {
  }
}
