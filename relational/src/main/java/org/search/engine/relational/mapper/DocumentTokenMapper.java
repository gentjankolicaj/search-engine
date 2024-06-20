package org.search.engine.relational.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.search.engine.relational.domain.DocumentToken;
import org.springframework.jdbc.core.RowMapper;

public class DocumentTokenMapper implements RowMapper<DocumentToken> {

  @Override
  public DocumentToken mapRow(ResultSet rs, int rowNum) throws SQLException {
    return new DocumentToken(rs.getString("token_value"), rs.getLong("document_id"),
        rs.getInt("token_index"));
  }
}
