package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.service.TwitterService;

public class TwitterCLIRunner {

  private static boolean LOCATION_FLAG;
  private static Double longitude;
  private static Double latitude;
  private static TwitterService service;

  public TwitterCLIRunner(TwitterService twitterService) {
    this.service = twitterService;
  }

  public static void run(String[] args) {

    if (args.length == 2) {
      LOCATION_FLAG = false;
    } else if (args.length == 3) {
      LOCATION_FLAG = true;
      String[] coordinates = args[2].split(":");

      if (coordinates.length != 2) {
        throw new IllegalArgumentException("Invalid Location String.");
      }

      try {
        longitude = Double.parseDouble(coordinates[0]);
        latitude = Double.parseDouble(coordinates[1]);
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Invalid Location String.");
      }

      if (longitude > 180 || longitude < -180 || latitude > 90 || latitude < -90) {
        throw new IllegalArgumentException("Invalid geo location.");
      }
    } else {
      throw new IllegalArgumentException("USAGE: Action Text (Location = Optional)");
    }

    switch (args[0]) {
      case "post":
        if (LOCATION_FLAG) {
          service.postTweet(args[1], latitude, longitude);
        } else {
          service.postTweet(args[1], null, null);
        }
        break;
      case "get":
        service.showTweet(args[1], null);
        break;
      case "delete":
        service.deleteTweets(args[1].split(","));
        break;
      case "default":
        System.out.println("Invalid Action. Valid actions are post, get, or delete");
    }
  }
}
