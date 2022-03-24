package org.search.engine.relational.service;

import lombok.extern.slf4j.Slf4j;
import org.search.engine.relational.dao.DocumentTokenDao;
import org.search.engine.relational.dto.command.Command;
import org.search.engine.relational.dto.result.QueryResult;
import org.search.engine.relational.producer.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
public class QueryServiceImpl implements QueryService {

    private final RabbitMQProducer rabbitMQProducer;
    private final DocumentTokenDao documentTokenDao;

    @Autowired
    public QueryServiceImpl(RabbitMQProducer rabbitMQProducer, DocumentTokenDao documentTokenDao) {
        this.rabbitMQProducer = rabbitMQProducer;
        this.documentTokenDao = documentTokenDao;
    }

    //todo : to fully implement with dao
    @Override
    public void query(Command command) {
        log.info("Query called with command " + command);
        //todo : dao method invocation here
        rabbitMQProducer.publish(new QueryResult(2, Collections.singletonList(command.getParams())));
    }
}
