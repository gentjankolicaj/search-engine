package org.search.engine.console.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.search.engine.console.exception.JsonException;
import org.search.engine.console.message.Message;
import org.search.engine.console.result.DocumentToken;
import org.search.engine.console.result.Result;


public class JsonUtil {

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  private static final TypeReference<List<DocumentToken>> LIST_DOCUMENT_TOKEN_TYPE = new TypeReference<List<DocumentToken>>() {
  };
  private static final TypeReference<Message<Object, Result>> MESSAGE_TYPE = new TypeReference<Message<Object, Result>>() {
  };

  public static String toJson(Object object) throws JsonProcessingException {
    if (object == null) {
      throw new JsonException("Can't process null objects");
    }
    return OBJECT_MAPPER.writeValueAsString(object);
  }

  public static Message forJsonToObject(String s) throws JsonProcessingException {
    if (s == null) {
      throw new JsonException("Can't process null objects");
    }
    return OBJECT_MAPPER.readValue(s, Message.class);
  }

  public static Message fromJsonToObject(byte[] array) {
    if (array == null) {
      throw new JsonException("Can't process null objects");
    }
    try {
      return OBJECT_MAPPER.readValue(array, Message.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }


}
