package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.dao.TwitterRestDao;
import ca.jrvs.apps.twitter.dto.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.lang.reflect.Field;
import java.util.HashMap;

public class TwitterServiceImp implements TwitterService {


  @Override
  public void postTweet(String text, Double latitude, Double longitude) {

  }

  @Override
  public void showTweet(String id, String[] fields) {
    TwitterRestDao dao = new TwitterRestDao();
    Tweet tweet = dao.findById(id);
    HashMap<String, Object> m = new HashMap<>();
    try {
      for (String f : fields) {
        Field field = Tweet.class.getField(f);
        m.put(f, field.get(tweet).toString());
      }
      System.out.println(JsonUtil.toJsonFromObject(m, true, true));
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void deleteTweets(String[] ids) {

  }
}
