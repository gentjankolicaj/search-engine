package org.search.engine.elastic.service;

import org.search.engine.elastic.dto.command.Command;

public interface DocumentService {

  void index(Command command);

  void query(Command command);
}
