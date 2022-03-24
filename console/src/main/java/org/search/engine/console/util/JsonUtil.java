package org.search.engine.console.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.search.engine.console.exception.JsonException;
import org.search.engine.console.message.Message;


public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object object) throws JsonProcessingException {
        if (object == null) {
            throw new JsonException("Can't process null objects");
        }
        return objectMapper.writeValueAsString(object);
    }

    public static Message forJsonToObject(String s) throws JsonProcessingException {
        if (s == null) {
            throw new JsonException("Can't process null objects");
        }
        return objectMapper.readValue(s, Message.class);
    }

    public static Message fromJsonToObject(byte[] array) {
        if (array == null) {
            throw new JsonException("Can't process null objects");
        }
        try {
            return objectMapper.readValue(array, Message.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
