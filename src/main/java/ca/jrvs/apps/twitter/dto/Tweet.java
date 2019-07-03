package ca.jrvs.apps.twitter.dto;

public class Tweet {

  public String created_at;
  public String id_str;
  public String text;
  public Entities entities;
  public Coordinates coordinates;
  public Long retweet_count;
  public Long favorite_count;
  public boolean favorited;
  public boolean retweeted;
}


