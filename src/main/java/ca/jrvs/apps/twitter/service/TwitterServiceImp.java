package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.TwitterRestDao;
import ca.jrvs.apps.twitter.dto.Entities;
import ca.jrvs.apps.twitter.dto.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.lang.reflect.Field;
import java.util.HashMap;

public class TwitterServiceImp implements TwitterService {

  private TwitterRestDao dao;

  public TwitterServiceImp() {
    dao = new TwitterRestDao();
  }

  public void postTweet(String text) {
    postTweet(text, null, null);
  }

  @Override
  public void postTweet(String text, Double latitude, Double longitude) {
    Tweet tweet = new Tweet(text, latitude, longitude);
    String json = getTweetFields(dao.create(tweet));
    System.out.println(json);

  }


  @Override
  public void showTweet(String id, String[] fields) {
    Tweet tweet = dao.findById(id);
    String json = getTweetFields(tweet, fields);
    System.out.println(json);

  }

  @Override
  public void deleteTweets(String[] ids) {

  }

  private String getTweetFields(Tweet tweet) {
    String json = "";
    try {
      json = JsonUtil.toJsonFromObject(tweet, true, false);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return json;
  }

  private String getTweetFields(Tweet tweet, String[] fields) {
    HashMap<String, Object> m = new HashMap<>();
    String json = "";
    try {
      for (String f : fields) {
        Field field;
        if (f.matches("(hashtags|user_mentions)")) {
          field = Entities.class.getField(f);
          m.put(f, field.get(tweet.entities));
        } else {
          field = Tweet.class.getField(f);
          m.put(f, field.get(tweet));
        }

      }
      json = JsonUtil.toJsonFromObject(m, true, true);
    } catch (NoSuchFieldException e) {
      System.out.println("Could not find field: " + e.getMessage());
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return json;
  }

}
