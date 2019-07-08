package ca.jrvs.apps.twitter.service;

import static ca.jrvs.apps.twitter.util.JsonUtil.toJsonFromObject;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ca.jrvs.apps.twitter.dao.TwitterRestDao;
import ca.jrvs.apps.twitter.dto.Tweet;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

public class TwitterServiceImpUnitTest {

  private static Tweet tweet;
  private static TwitterRestDao dao;
  private static TwitterServiceImp service;
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;

  @BeforeClass
  public static void setup() {
    dao = Mockito.mock(TwitterRestDao.class);
    tweet = new Tweet("This is a tweet", 60.0, 60.0);
    when(dao.create(any())).thenAnswer(i -> i.getArguments()[0]);
    when(dao.findById(any())).thenReturn(tweet);
    when(dao.deleteById(any())).thenReturn(tweet);
    service = new TwitterServiceImp(dao);
  }

  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @After
  public void restoreStreams() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  @Test
  public void apostTweet() throws JsonProcessingException {
    service.postTweet("This is a tweet", 60.0, 60.0);
    assertTweet();
  }

  @Test
  public void bshowTweet() throws JsonProcessingException {
    service.showTweet("1234");
    assertTweet();
  }

  @Test
  public void deleteTweets() throws JsonProcessingException {
    String[] ids = {"1", "2", "3"};
    service.deleteTweets(ids);
    verify(dao, times(3)).deleteById(anyString());
  }

  private void assertTweet() throws JsonProcessingException {
    assertThat(outContent.toString(),
        containsString(toJsonFromObject(tweet, true, false)));
  }
}