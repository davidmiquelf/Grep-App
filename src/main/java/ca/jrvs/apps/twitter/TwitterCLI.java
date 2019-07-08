package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.dao.CrdRepository;
import ca.jrvs.apps.twitter.dao.TwitterRestDao;
import ca.jrvs.apps.twitter.dao.helper.ApacheHttpHelper;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.service.TwitterService;
import ca.jrvs.apps.twitter.service.TwitterServiceImp;

public class TwitterCLI {

  public static void main(String[] args) {

    HttpHelper helper = new ApacheHttpHelper();
    CrdRepository dao = new TwitterRestDao(helper);
    TwitterService service = new TwitterServiceImp(dao);
    TwitterCLIRunner runner = new TwitterCLIRunner(service);
    runner.run(args);

  }
}
