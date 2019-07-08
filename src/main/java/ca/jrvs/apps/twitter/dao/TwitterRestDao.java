package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dto.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidParameterException;
import org.apache.http.HttpResponse;

public class TwitterRestDao implements CrdRepository<Tweet, String> {

  private static final String API_BASE_URI = "https://api.twitter.com";
  private static final String POST_PATH = "/1.1/statuses/update.json?status=";
  private static final String SHOW_PATH = "/1.1/statuses/show.json?id=";
  private static final String DELETE_PATH = "/1.1/statuses/destroy/";
  private static final String AND_FLAG = "&";
  private static final String EQ_FLAG = "=";
  private HttpHelper helper;

  public TwitterRestDao(HttpHelper helper) {
    this.helper = helper;
  }

  public TwitterRestDao() {
    this.helper = new ApacheHttpHelper();
  }

  @Override
  public Tweet create(Tweet tweet) {
    Tweet t = null;
    try {
      URI uri = getPostUri(tweet);
      HttpResponse response = helper.httpPost(uri);
      t = JsonUtil.toObjectFromResponse(response, Tweet.class);
    } catch (URISyntaxException | UnsupportedEncodingException e) {
      System.out.println("Invalid status text. Please use normal characters.");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return t;
  }

  @Override
  public Tweet findById(String id) {
    Tweet tweet = null;
    try {
      URI uri = getFindURI(id);
      HttpResponse response = helper.httpGet(uri);
      tweet = JsonUtil.toObjectFromResponse(response, Tweet.class);

    } catch (IOException | InvalidParameterException e) {
      throw new RuntimeException("Invalid id string.", e);
    }
    return tweet;
  }

  @Override
  public Tweet deleteById(String id) {
    Tweet tweet;
    try {
      URI uri = getDeleteURI(id);
      HttpResponse response = helper.httpPost(uri);
      tweet = JsonUtil.toObjectFromResponse(response, Tweet.class);
    } catch (IOException e) {
      throw new RuntimeException("Failed to convert response to tweet.", e);
    }
    return tweet;
  }

  private void validateIdString(String id) throws InvalidParameterException {
    if (id == null) {
      throw new InvalidParameterException("Cannot find Id.");
    }
    id.chars()
        .filter(c -> (c < '0' || c > '9'))
        .limit(1)
        .forEach(c -> {
          throw new InvalidParameterException("Invalid Id String: " + id);
        });
  }

  private URI getPostUri(Tweet tweet) throws URISyntaxException, UnsupportedEncodingException {
    String status = URLEncoder.encode(tweet.text, StandardCharsets.UTF_8.name());
    String uriString = API_BASE_URI + POST_PATH + status;

    if (tweet.coordinates != null) {
      uriString = appendURIParam(uriString, "display_coordinates", "true");
      uriString = appendURIParam(uriString, "long", tweet.coordinates.coordinates[0].toString());
      uriString = appendURIParam(uriString, "lat", tweet.coordinates.coordinates[1].toString());
    }
    URI uri = new URI(uriString);
    return uri;
  }

  private URI getFindURI(String id) {
    validateIdString(id);
    URI uri = null;
    try {
      uri = new URI(API_BASE_URI + SHOW_PATH + id);
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    return uri;
  }

  private URI getDeleteURI(String id) {
    validateIdString(id);
    URI uri = null;
    try {
      uri = new URI(API_BASE_URI + DELETE_PATH + id + ".json");
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    return uri;
  }

  private String appendURIParam(String uri, String param, String val) {
    return uri + AND_FLAG + param + EQ_FLAG + val;
  }
}
