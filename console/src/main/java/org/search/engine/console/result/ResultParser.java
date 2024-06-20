package org.search.engine.console.result;

import org.search.engine.console.exception.ResultParseException;

public interface ResultParser {

  String parse(Result result) throws ResultParseException;
}
