package ca.jrvs.apps.twitter;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import ca.jrvs.apps.twitter.dao.CrdRepository;
import ca.jrvs.apps.twitter.dao.TwitterRestDao;
import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dto.Tweet;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.service.TwitterServiceImp;
import ca.jrvs.apps.twitter.util.JsonUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TwitterCLITest {

  private static HttpHelper helper;
  private static CrdRepository<Tweet, String> dao;
  private static TwitterService service;
  private static TwitterCLIRunner runner;
  private static Tweet tweet1;
  private static Tweet tweet2;
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;

  @BeforeClass
  public static void setup() {
    helper = new ApacheHttpHelper();
    dao = new TwitterRestDao(helper);
    service = new TwitterServiceImp(dao);
    runner = new TwitterCLIRunner(service);
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
  public void testAPost() throws IOException {
    String[] args1 = {"post", "This is a test tweet", "20:20"};
    runner.run(args1);

    tweet1 = JsonUtil.toObjectFromJson(outContent.toString(), Tweet.class);

    assertEquals(tweet1.text, "This is a test tweet");
    assertEquals(tweet1.coordinates.coordinates[1], (Double) 20.0);

  }

  @Test
  public void testBGet() throws IOException {

    String[] args2 = {"get", tweet1.id};
    runner.run(args2);

    tweet2 = JsonUtil.toObjectFromJson(outContent.toString(), Tweet.class);

    assertEquals(JsonUtil.toJsonFromObject(tweet1, true, true),
        JsonUtil.toJsonFromObject(tweet2, true, true));
  }

  @Test
  public void testCDelete() {

    String[] args3 = {"delete", tweet1.id};
    runner.run(args3);

    assertThat(outContent.toString(), containsString("Deleted " + tweet1.id + " Successfully."));


  }

}
