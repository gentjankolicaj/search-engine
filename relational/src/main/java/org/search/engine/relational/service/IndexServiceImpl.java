package org.search.engine.relational.service;

import lombok.extern.slf4j.Slf4j;
import org.search.engine.relational.dao.DocumentTokenDao;
import org.search.engine.relational.dto.command.Command;
import org.search.engine.relational.dto.result.IndexResult;
import org.search.engine.relational.producer.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class IndexServiceImpl implements IndexService {

    private final RabbitMQProducer rabbitMQProducer;
    private final DocumentTokenDao documentTokenDao;

    @Autowired
    public IndexServiceImpl(RabbitMQProducer rabbitMQProducer, DocumentTokenDao documentTokenDao) {
        this.rabbitMQProducer = rabbitMQProducer;
        this.documentTokenDao = documentTokenDao;
    }

    //todo : to fully implement with dao
    @Override
    public void index(Command command) {
        log.info("Index called with command " + command);
        List<Object> results = new ArrayList<>();
        results.add("index");
        results.add("ok");
        results.add(command.getParams().get(1));
        //todo : dao method invocation here
        rabbitMQProducer.publish(new IndexResult(1, results));
    }
}
