package ca.jrvs.apps.twitter.service;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class TwitterServiceImpTest {

  private TwitterServiceImp service;

  @Before
  public void setup() {
    service = new TwitterServiceImp();
    service.postTweet("this is a test tweet");
  }

  @Test
  public void showTweet() {
    ArrayList<String> fields = new ArrayList<>();
    fields.add("id");
    fields.add("text");
    fields.add("hashtags");
    String[] f = {};
    service.showTweet("1146867792609783808", fields.toArray(f));
  }

  @Test
  public void deleteTweets() {
  }
}