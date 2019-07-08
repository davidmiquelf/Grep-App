package ca.jrvs.apps.twitter.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Tweet {

  public String created_at;
  public String id;
  public String text;
  public Entities entities;
  public Coordinates coordinates;
  public Long retweet_count;
  public Long favorite_count;
  public boolean favorited;
  public boolean retweeted;

  @SuppressWarnings("unused")
  public Tweet() {
    super();
  }

  public Tweet(String text) {
    this.text = text;
  }

  public Tweet(String text, Double longitude, Double latitude) {
    this.text = text;
    if (longitude != null && latitude != null) {
      this.coordinates = new Coordinates();
      Double[] c = {longitude, latitude};
      coordinates.coordinates = c;
    }
  }
}


