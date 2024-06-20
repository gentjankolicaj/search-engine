package org.search.engine.relational.producer;

import org.search.engine.relational.dto.result.Result;

public interface Producer {

  void produce(Result result) throws RuntimeException;

}
