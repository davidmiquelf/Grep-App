package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.CrdRepository;
import ca.jrvs.apps.twitter.dto.Entities;
import ca.jrvs.apps.twitter.dto.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;

public class TwitterServiceImp implements TwitterService {

  private CrdRepository<Tweet, String> dao;

  public TwitterServiceImp(CrdRepository dao) {
    this.dao = dao;
  }

  @Override
  public void postTweet(String text, Double latitude, Double longitude) {
    Tweet tweet;
    if (latitude != null && longitude != null) {
      tweet = new Tweet(text, latitude, longitude);
    } else {
      tweet = new Tweet(text);
    }
    tweet = dao.create(tweet);
    String json = getTweetFields(tweet);
    if (tweet.text == null) {
      System.out.println("Twitter refused to make this tweet.");
    } else {
      System.out.println(json);
    }
  }

  public void showTweet(String id) {
    showTweet(id, null);
  }

  @Override
  public void showTweet(String id, String[] fields) {
    Tweet tweet = dao.findById(id);
    String json;
    if (fields == null) {
      json = getTweetFields(tweet);
    } else {
      json = getTweetFields(tweet, fields);
    }
    System.out.println(json);

  }

  @Override
  public void deleteTweets(String[] ids) {
    Arrays.stream(ids)
        .map(dao::deleteById)
        .forEach(t ->
            System.out.println(
                "Deleted " + t.id + " Successfully."
            ));
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
      json = JsonUtil.toJsonFromObject(m, true, false);
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