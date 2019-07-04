package ca.jrvs.apps.twitter.service;

import org.junit.Test;

public class TwitterServiceImpTest {

  @Test
  public void postTweet() {
  }

  @Test
  public void showTweet() {
    TwitterServiceImp service = new TwitterServiceImp();
    String[] fields = {"text", "id"};
    service.showTweet("1146867792609783808", fields);
  }

  @Test
  public void deleteTweets() {
  }
}