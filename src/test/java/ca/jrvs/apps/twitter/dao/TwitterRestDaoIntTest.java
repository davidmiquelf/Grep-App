package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.assertEquals;

import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dto.Tweet;
import java.security.InvalidParameterException;
import org.junit.Before;
import org.junit.Test;

public class TwitterRestDaoIntTest {

  private static TwitterRestDao dao;
  private static HttpHelper helper;
  private Tweet tweet;

  @Before
  public void setup() {
    helper = new ApacheHttpHelper();
    dao = new TwitterRestDao(helper);
    Tweet dummy = new Tweet("This is a test tweet");
    tweet = dao.create(dummy);
  }

  @Test
  public void createShowDelete() {
    assertEquals(tweet.text, "This is a test tweet");

    Tweet twt = dao.findById("1146867792609783808");
    assertEquals(twt.id, "1146867792609783808");
    assertEquals(twt.text,
        "I'm making an app that acts as a twitter client. "
            + "This tweet is just for testing."
            + "\n#hashtag #alsohashtag\n@LemonardoDavid");
    assertEquals(twt.entities.hashtags[0].text, "hashtag");
    assertEquals(twt.entities.hashtags[1].text, "alsohashtag");

    twt = dao.findById(tweet.id);
    assertEquals(twt.text, tweet.text);

    twt = dao.deleteById(tweet.id);
    assertEquals(twt.text, "This is a test tweet");
    try {
      dao.findById(tweet.id);
    } catch (InvalidParameterException e) {
      assertEquals(e.getMessage(), "Cannot find Id.");
    }

  }
}
