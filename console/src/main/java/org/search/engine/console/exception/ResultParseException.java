package org.search.engine.console.exception;

public class ResultParseException extends RuntimeException {

  public ResultParseException() {
  }

  public ResultParseException(String message) {
    super(message);
  }

  public ResultParseException(String message, Throwable cause) {
    super(message, cause);
  }

  public ResultParseException(Throwable cause) {
    super(cause);
  }

  public ResultParseException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
