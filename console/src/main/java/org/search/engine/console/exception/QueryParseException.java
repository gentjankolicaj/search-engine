package org.search.engine.console.exception;

public class QueryParseException extends CommandParseException {

  public QueryParseException() {
  }

  public QueryParseException(String message) {
    super(message);
  }

  public QueryParseException(String message, Throwable cause) {
    super(message, cause);
  }

  public QueryParseException(Throwable cause) {
    super(cause);
  }

  public QueryParseException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
