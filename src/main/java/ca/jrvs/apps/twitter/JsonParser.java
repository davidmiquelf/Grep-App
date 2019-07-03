package ca.jrvs.apps.twitter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;

public class JsonParser {

  private static ObjectMapper mapper;

  public JsonParser() {
    mapper = new ObjectMapper();
  }

  /**
   * Convert a java object to JSON string
   *
   * @param object input object
   * @return JSON String
   */
  public static String toJson(
      Object object, boolean prettyJson,
      boolean includeNullValues) throws JsonProcessingException {

    //Options.
    if (prettyJson) {
      mapper.enable(SerializationFeature.INDENT_OUTPUT);
    } else {
      mapper.disable(SerializationFeature.INDENT_OUTPUT);
    }

    if (includeNullValues) {
      mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    } else {
      mapper.enable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    return mapper.writeValueAsString(object);
  }

  /**
   * Parse JSON string to a object
   *
   * @param json JSON str
   * @param clazz object class
   * @param <T> Type
   * @return Object
   */
  public static <T> T toObjectFromJson(
      String json, Class clazz) throws IOException {
    return (T) mapper.readValue(json, clazz);
  }

}
