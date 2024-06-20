package org.search.engine.console.exception;

public class IndexParseException extends CommandParseException {

  public IndexParseException() {
  }

  public IndexParseException(String message) {
    super(message);
  }

  public IndexParseException(String message, Throwable cause) {
    super(message, cause);
  }

  public IndexParseException(Throwable cause) {
    super(cause);
  }

  public IndexParseException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
