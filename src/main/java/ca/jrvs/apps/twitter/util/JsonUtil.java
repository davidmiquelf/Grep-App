package ca.jrvs.apps.twitter.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class JsonUtil {

  private static ObjectMapper mapper = new ObjectMapper()
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


  /**
   * Convert a java object to JSON string
   *
   * @param object input object
   * @return JSON String
   */
  public static String toJsonFromObject(
      Object object, boolean prettyJson,
      boolean includeNullValues) throws JsonProcessingException {

    //Options.
    if (prettyJson) {
      mapper.enable(SerializationFeature.INDENT_OUTPUT);
    } else {
      mapper.disable(SerializationFeature.INDENT_OUTPUT);
    }

    if (includeNullValues) {
      mapper.setSerializationInclusion(Include.ALWAYS);
    } else {
      mapper.setSerializationInclusion(Include.NON_NULL);
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

  public static String toJsonFromResponse(HttpResponse response) throws IOException {
    return EntityUtils.toString(response.getEntity());
  }

  public static <T> T toObjectFromResponse(
      HttpResponse response, Class clazz) throws IOException {
    return toObjectFromJson(toJsonFromResponse(response), clazz);
  }

}
