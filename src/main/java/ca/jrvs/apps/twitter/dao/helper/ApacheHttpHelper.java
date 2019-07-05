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

  private static String CONSUMER_KEY = "";
  private static String CONSUMER_SECRET = "";
  private static String ACCESS_TOKEN = "";
  private static String TOKEN_SECRET = "";
  private OAuthConsumer consumer;

  public ApacheHttpHelper() {
    CONSUMER_KEY = System.getenv("CONSUMER_KEY");
    CONSUMER_SECRET = System.getenv("CONSUMER_SECRET");
    ACCESS_TOKEN = System.getenv("ACCESS_TOKEN");
    TOKEN_SECRET = System.getenv("TOKEN_SECRET");
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
