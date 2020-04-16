
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.Status;
import twitter4j.TwitterException;

public class Twitterbot {
	
	public static void main (String[] args) {
		NewTweet();
	}

	public static void NewTweet() {
	Twitter twitter = TwitterFactory.getSingleton();
	String tweet = "i wrote this on java lmao";

	try
	{
		Status status = twitter.updateStatus(tweet);
		System.out.println("success" + status.getText());
	}catch(
	TwitterException e)
	{

		e.printStackTrace();

	}
}
}