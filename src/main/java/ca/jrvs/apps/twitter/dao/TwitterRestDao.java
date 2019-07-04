package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dto.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidParameterException;
import org.apache.http.HttpResponse;

public class TwitterRestDao implements CrdRepository<Tweet, String> {

  private ApacheHttpHelper helper;

  public TwitterRestDao() {
    helper = new ApacheHttpHelper();
  }

  @Override
  public Tweet save(Tweet entity) {
    return null;
  }

  @Override
  public Tweet findById(String id) {
    Tweet tweet = null;
    try {
      validateIdString(id);
      URI uri = new URI("https://api.twitter.com/1.1/statuses/show.json?id=" + id);
      HttpResponse response = helper.httpGet(uri);
      String json = JsonUtil.toJsonFromResponse(response);
      tweet = JsonUtil.toObjectFromJson(json, Tweet.class);

    } catch (URISyntaxException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InvalidParameterException e) {
      System.out.println(e.getMessage());
    }
    return tweet;
  }

  @Override
  public Tweet deleteById(String id) {
    Tweet tweet;
    try {
      validateIdString(id);
      HttpResponse response = helper.httpPost(
          new URI("https://api.twitter.com/1.1/statuses/destroy/" + id + ".json")
      );
      tweet = JsonUtil.toObjectFromResponse(response, Tweet.class);
    } catch (IOException e) {
      throw new RuntimeException("failed to convert response to tweet.", e);
    } catch (URISyntaxException e) {
      throw new RuntimeException("id validation failed, or API changed.", e);
    }
    return tweet;
  }

  private void validateIdString(String id) throws InvalidParameterException {
    id.chars()
        .filter(c -> (c < '0' || c > '9'))
        .forEach(c -> {
          throw new InvalidParameterException("Invalid Id String.");
        });

  }
}
