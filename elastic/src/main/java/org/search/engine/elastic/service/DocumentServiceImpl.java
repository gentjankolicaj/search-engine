package org.search.engine.elastic.service;

import lombok.extern.slf4j.Slf4j;
import org.search.engine.elastic.dao.DocumentDao;
import org.search.engine.elastic.dto.command.Command;
import org.search.engine.elastic.dto.result.IndexResult;
import org.search.engine.elastic.dto.result.QueryResult;
import org.search.engine.elastic.producer.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class DocumentServiceImpl implements DocumentService{

    private final DocumentDao documentDao;
    private final RabbitMQProducer rabbitMQProducer;

    @Autowired
    public DocumentServiceImpl(DocumentDao documentDao, RabbitMQProducer rabbitMQProducer) {
        this.documentDao = documentDao;
        this.rabbitMQProducer = rabbitMQProducer;
    }



    @Override
    public void index(Command command) {
        log.info("Index called with command " + command);
        try {
            List<String> params = command.getParams();
            //todo : to call documentDao.index(params) here.
            List<Object> results = new ArrayList<>();
            results.add("ok");
            results.add(params.get(1));
            rabbitMQProducer.produce(new IndexResult(1, results));
            log.info("Published to result queue : " + results);
        } catch (Exception e) {
            log.error(e.getMessage());
            List<Object> results = new ArrayList<>();
            results.add("error");
            results.add(e.getMessage());
            rabbitMQProducer.produce(new IndexResult(-1, results));
            log.info("Published to result queue : " + results);
        }

    }

    @Override
    public void query(Command command) {
        log.info("Query with command " + command + " executed.");
        try {
            List<String> params = command.getParams();
            //todo : to call documentDao.query(params) here.
            rabbitMQProducer.produce(new QueryResult<>(2, null));
            log.info("Published to result queue : " + null);
        } catch (Exception e) {
            log.error(e.getMessage());
            rabbitMQProducer.produce(new QueryResult<>(-2, Arrays.asList(e.getMessage())));
            log.info("Published to result queue : " + e.getMessage());
        }
    }
}
