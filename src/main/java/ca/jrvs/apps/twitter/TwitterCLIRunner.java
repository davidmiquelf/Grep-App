package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TwitterCLIRunner {

  private static boolean LOCATION_FLAG;
  private static Double longitude;
  private static Double latitude;
  private static TwitterService service;

  @Autowired
  public TwitterCLIRunner(TwitterService twitterService) {
    this.service = twitterService;
  }

  public void run(String[] args) {

    parseLocation(args);

    switch (args[0]) {
      case "post":
        postTweet(args);
        break;
      case "get":
        showTweet(args);
        break;
      case "delete":
        deleteTweet(args);
        break;
      case "default":
        System.out.println("USAGE: TwitterCLI post|show|delete");
        break;
    }
  }

  protected void postTweet(String[] args) {
    if (LOCATION_FLAG) {
      service.postTweet(args[1], latitude, longitude);
    } else {
      service.postTweet(args[1], null, null);
    }
  }

  protected void showTweet(String[] args) {
    service.showTweet(args[1], null);
  }

  protected void deleteTweet(String[] args) {
    service.deleteTweets(args[1].split(","));
  }

  protected void parseLocation(String[] args) {
    if (args.length < 2) {
      throw new IllegalArgumentException("USAGE: TwitterCLI post|show|delete text [location]");

    } else if (args.length >= 3 && args[2].matches("-?\\d+\\.?\\d*:-?\\d+\\.?\\d*")) {
      LOCATION_FLAG = true;
      String[] coordinates = args[2].split(":");

      if (coordinates.length != 2) {
        throw new IllegalArgumentException("Invalid Location String.");
      }

      try {
        longitude = Double.parseDouble(coordinates[0]);
        latitude = Double.parseDouble(coordinates[1]);
        if (longitude > 180 || longitude < -180 || latitude > 90 || latitude < -90) {
          throw new IllegalArgumentException("Invalid geo location.");
        }
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Invalid Location String.");
      }

    } else {
      LOCATION_FLAG = false;
    }
  }
}
