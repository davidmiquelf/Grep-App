package ca.jrvs.apps.twitter.dao.helper;

import ca.jrvs.apps.twitter.dto.Tweet;
import ca.jrvs.apps.twitter.util.JsonUtil;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class ApacheHttpHelperIntTest {

  public static void main(String[] Args) {
    ApacheHttpHelper ahh = new ApacheHttpHelper();
    JsonUtil parser = new JsonUtil();
    try {
      System.out.println("test POST");
      ahh.httpPost(
          new URI("https://api.twitter.com/1.1/statuses/update.json?status="
              + "This%20is%20a%20test%20tweet")
      );

      System.out.println("test GET");
      HttpResponse response2 = ahh.httpGet(
          new URI("https://api.twitter.com/1.1/statuses/"
              + "user_timeline.json?screen_name=LemonardoDavid&count=1"));
      System.out.println(response2.getStatusLine());

      String json = EntityUtils.toString(response2.getEntity());

      Tweet[] tweets =
          parser.toObjectFromJson(json, Tweet[].class);
      System.out.println(parser.toJsonFromObject(tweets, true, true));

      String idstr = tweets[0].id_str;

      System.out.println("test Delete");

      HttpResponse response3 = ahh.httpPost(
          new URI("https://api.twitter.com/1.1/statuses/destroy/" + idstr + ".json")
      );

      System.out.println(response3.getStatusLine());

    } catch (URISyntaxException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }


  }
}