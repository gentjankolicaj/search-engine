package org.search.engine.relational.service;

import lombok.extern.slf4j.Slf4j;
import org.search.engine.relational.builder.index.IndexBuilder;
import org.search.engine.relational.builder.index.IndexSql;
import org.search.engine.relational.dao.DocumentTokenDao;
import org.search.engine.relational.dto.command.Command;
import org.search.engine.relational.dto.result.IndexResult;
import org.search.engine.relational.producer.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class IndexServiceImpl implements IndexService {

    private final RabbitMQProducer rabbitMQProducer;
    private final DocumentTokenDao documentTokenDao;
    private final IndexBuilder indexBuilder;


    @Autowired
    public IndexServiceImpl(RabbitMQProducer rabbitMQProducer, DocumentTokenDao documentTokenDao, IndexBuilder indexBuilder) {
        this.rabbitMQProducer = rabbitMQProducer;
        this.documentTokenDao = documentTokenDao;
        this.indexBuilder = indexBuilder;
    }

    @Override
    public void index(Command command) {
        log.info("Index called with command " + command);
        try {
            List<String> params = command.getParams();
            Map<String, IndexSql> queryMap = indexBuilder.setParams(params)
                    .build();
            documentTokenDao.update(queryMap);
            List<Object> results = new ArrayList<>();
            results.add("ok");
            results.add(params.get(1));
            rabbitMQProducer.publish(new IndexResult(1, results));
            log.info("Published to result queue : " + results);
        } catch (Exception e) {
            log.error(e.getMessage());
            List<Object> results = new ArrayList<>();
            results.add("error");
            results.add(e.getMessage());
            rabbitMQProducer.publish(new IndexResult(-1, results));
            log.info("Published to result queue : " + results);
        }

    }
}
