package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.assertEquals;

import ca.jrvs.apps.twitter.dto.Tweet;
import org.junit.Before;
import org.junit.Test;

public class TwitterRestDaoUnitTest {

  private static TwitterRestDao dao;

  @Before
  public void before() {
    dao = new TwitterRestDao();

  }

  @Test
  public void create() {
  }

  @Test
  public void findById() {

    Tweet tweet = dao.findById("1146867792609783808");
    assertEquals(tweet.id, "1146867792609783808");
    assertEquals(tweet.text,
        "I'm making an app that acts as a twitter client. "
            + "This tweet is just for testing."
            + "\n#hashtag #alsohashtag\n@LemonardoDavid");
    assertEquals(tweet.entities.hashtags[0].text, "hashtag");
    assertEquals(tweet.entities.hashtags[1].text, "alsohashtag");
  }

  @Test
  public void deleteById() {

  }


}