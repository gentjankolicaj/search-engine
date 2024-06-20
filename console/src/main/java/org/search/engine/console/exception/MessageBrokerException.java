package org.search.engine.console.exception;

public class MessageBrokerException extends RuntimeException {

  public MessageBrokerException() {
  }

  public MessageBrokerException(String message) {
    super(message);
  }

  public MessageBrokerException(String message, Throwable cause) {
    super(message, cause);
  }

  public MessageBrokerException(Throwable cause) {
    super(cause);
  }

  public MessageBrokerException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
