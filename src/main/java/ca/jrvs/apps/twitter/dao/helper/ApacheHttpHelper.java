package ca.jrvs.apps.twitter.dao.helper;

import ca.jrvs.apps.twitter.util.StringUtil;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class ApacheHttpHelper implements HttpHelper {

  private OAuthConsumer consumer;

  @Autowired
  public ApacheHttpHelper() {
    String consumerKey = System.getenv("consumerKey");
    String consumerSecret = System.getenv("consumerSecret");
    String accessToken = System.getenv("accessToken");
    String tokenSecret = System.getenv("tokenSecret");

    if (StringUtil.isEmpty(consumerKey, consumerSecret, accessToken, tokenSecret)) {
      throw new RuntimeException("Unable to detect key and tokens from System env");
    }

    this.consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
    this.consumer.setTokenWithSecret(accessToken, tokenSecret);
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
