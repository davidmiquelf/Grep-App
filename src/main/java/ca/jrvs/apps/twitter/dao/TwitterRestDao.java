package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.JsonParser;
import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dto.Tweet;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class TwitterRestDao implements CrdRepository<Tweet, String> {

  @Override
  public Tweet save(Tweet entity) {
    return null;
  }

  @Override
  public Tweet findById(String id) {
    if (!validateIdString(id)) {
      System.out.println("Id is not valid.");
      return null;
    }
    ApacheHttpHelper helper = new ApacheHttpHelper();
    Tweet tweet = null;

    try {
      URI uri = new URI("https://api.twitter.com/1.1/statuses/show.json?id=" + id);
      HttpResponse response = helper.httpGet(uri);
      String json = EntityUtils.toString(response.getEntity());
      tweet = JsonParser.toObjectFromJson(json, Tweet.class);

    } catch (URISyntaxException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return tweet;
  }

  @Override
  public Tweet deleteById(String id) {
    return null;
  }

  private boolean validateIdString(String id) {
    return id.matches("\\d+");
  }
}
