package ca.jrvs.apps.twitter.example;

import java.util.Arrays;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class TwitterApiTest {

  private static String CONSUMER_KEY = "COylz9pKLIxb1jT2ktViQfbNw";
  private static String CONSUMER_SECRET = "4jwFcnXd1aodWALIDxKnBVhpLTX1sLZ796PUtVlD1ENx9xcKgE";
  private static String ACCESS_TOKEN = "1094024554438164480-DBJhcq0v7svOZ8d2mAgNo5c7BQa1ne";
  private static String TOKEN_SECRET = "KbBXNolx2SaJHiSIfAT5QTpEzRMQ1fpQoIFFNtBG8ZW3R";

  public static void main(String[] args) throws
      Exception {
    //setup oauth
    OAuthConsumer consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
    consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);

    // create an HTTP GET request
    HttpGet request = new HttpGet(
        "https://api.twitter.com/1.1/users/search.json?q=realDonaldTrump");

    // sign the request (add headers)
    consumer.sign(request);
    System.out.println("Http Request Headers:");

    Arrays.stream(request.getAllHeaders()).forEach(System.out::println);
    // send/execute the request
    HttpClient httpClient = new DefaultHttpClient();
    HttpResponse response = httpClient.execute(request);

    System.out.println(EntityUtils.toString(response.getEntity()));
  }

}