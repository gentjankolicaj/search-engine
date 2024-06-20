package org.search.engine.relational.service;

import org.search.engine.relational.dto.command.Command;

public interface IndexService {

  void index(Command command);

}
