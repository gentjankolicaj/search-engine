package org.search.engine.relational.service;

import org.search.engine.relational.dto.command.Command;


public interface QueryService {

  void query(Command Command);
}
