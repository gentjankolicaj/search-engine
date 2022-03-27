package org.search.engine.relational.service;

import lombok.extern.slf4j.Slf4j;
import org.search.engine.relational.builder.query.QueryBuilder;
import org.search.engine.relational.builder.query.QuerySql;
import org.search.engine.relational.dao.DocumentTokenDao;
import org.search.engine.relational.domain.DocumentToken;
import org.search.engine.relational.dto.command.Command;
import org.search.engine.relational.dto.result.QueryResult;
import org.search.engine.relational.producer.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class QueryServiceImpl implements QueryService {

    private final RabbitMQProducer rabbitMQProducer;
    private final DocumentTokenDao documentTokenDao;
    private final QueryBuilder queryBuilder;

    @Autowired
    public QueryServiceImpl(RabbitMQProducer rabbitMQProducer, DocumentTokenDao documentTokenDao, QueryBuilder queryBuilder) {
        this.rabbitMQProducer = rabbitMQProducer;
        this.documentTokenDao = documentTokenDao;
        this.queryBuilder = queryBuilder;
    }

    @Override
    public void query(Command command) {
        log.info("Query with command " + command + " executed.");
        try {
            List<String> params = command.getParams();
            //Get sublist without token keyword
            QuerySql<String> querySql = queryBuilder.setParams(params)
                    .build();
            //Get document tokens
            List<DocumentToken> documentTokens = documentTokenDao.readAll(querySql);
            rabbitMQProducer.publish(new QueryResult<>(2, documentTokens));
            log.info("Published to result queue : " + documentTokens);
        } catch (Exception e) {
            log.error(e.getMessage());
            rabbitMQProducer.publish(new QueryResult<>(-2, Arrays.asList(e.getMessage())));
            log.info("Published to result queue : " + e.getMessage());
        }
    }
}
