package org.search.engine.relational.dao;

import lombok.extern.slf4j.Slf4j;
import org.search.engine.relational.domain.DocumentToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Slf4j
public class DocumentTokenDaoImpl implements DocumentTokenDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DocumentTokenDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<DocumentToken> readAll() {
        return null;
    }

    @Override
    public DocumentToken read(String value) {
        return null;
    }

    @Override
    public void create(DocumentToken documentToken) {

    }

    @Override
    public void delete(DocumentToken documentToken) {

    }
}
