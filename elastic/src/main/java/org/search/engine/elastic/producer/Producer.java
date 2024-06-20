package org.search.engine.elastic.producer;

import org.search.engine.elastic.dto.result.Result;

public interface Producer {

  void produce(Result result) throws RuntimeException;
}
