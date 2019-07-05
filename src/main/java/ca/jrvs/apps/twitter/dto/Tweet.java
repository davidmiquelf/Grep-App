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
      coordinates.coordinates[0] = longitude;
      coordinates.coordinates[1] = latitude;
    }
  }

  public Double getLong() {
    return coordinates.coordinates[0];
  }

  public Double getLat() {
    return coordinates.coordinates[1];
  }

  public void setCoordinates(Double[] coordinates) {
    if (coordinates == null) {
      this.coordinates = new Coordinates();
    }
    this.coordinates.coordinates = coordinates;
  }
}


