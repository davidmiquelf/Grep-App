package ca.jrvs.apps.twitter.dao.helper;

import java.io.IOException;
import java.net.URI;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class ApacheHttpHelper implements HttpHelper {

  private static String CONSUMER_KEY = "COylz9pKLIxb1jT2ktViQfbNw";
  private static String CONSUMER_SECRET = "4jwFcnXd1aodWALIDxKnBVhpLTX1sLZ796PUtVlD1ENx9xcKgE";
  private static String ACCESS_TOKEN = "1094024554438164480-DBJhcq0v7svOZ8d2mAgNo5c7BQa1ne";
  private static String TOKEN_SECRET = "KbBXNolx2SaJHiSIfAT5QTpEzRMQ1fpQoIFFNtBG8ZW3R";
  private OAuthConsumer consumer;

  public ApacheHttpHelper() {
    this.consumer = new CommonsHttpOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
    this.consumer.setTokenWithSecret(ACCESS_TOKEN, TOKEN_SECRET);
  }

  @Override
  public HttpResponse httpPost(URI uri) {
    HttpPost request = new HttpPost(uri);
    return send(request);
  }

  @Override
  public HttpResponse httpPost(URI uri, StringEntity stringEntity) {
    HttpPost request = new HttpPost(uri);
    request.setEntity(stringEntity);
    return send(request);
  }

  @Override
  public HttpResponse httpGet(URI uri) {
    HttpGet request = new HttpGet(uri);
    return send(request);
  }

  private HttpResponse send(HttpRequestBase request) {
    HttpResponse response = null;
    try {

      this.consumer.sign(request);
      HttpClient httpClient = new DefaultHttpClient();
      response = httpClient.execute(request);

    } catch (OAuthException e) {
      e.printStackTrace();
    } catch (ClientProtocolException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return response;
  }
}
