
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.IDs;
import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.TwitterException;
import twitter4j.api.TweetsResources;
import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class Twitterbot {

	public static void main(String[] args) throws Exception {
		 //RetweetPicker(Long.parseLong("1284519230340292608"));
		// NewTweet();
		//ReplyTweet();
		
	}

	public static void NewTweet() {
		Twitter twitter = TwitterFactory.getSingleton();
		Scanner kb = new Scanner(System.in); // scanner

		System.out.println("Enter Tweet: "); // enter tweet
		String tweet = kb.nextLine(); // takes in the input

		try {
			Status status = twitter.updateStatus(tweet); // tweets it out
			System.out.println("success " + status.getId()); // confirms success
			
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

	public static void RetweetPicker(long statusID) throws NumberFormatException, TwitterException {

		Twitter twit = TwitterFactory.getSingleton();
		IDs stat = twit.getRetweeterIds(statusID, 100, -1); // get IDs of people that retweeted a specific status
		
		
		Random rng = new Random();
		int pick = rng.nextInt(stat.getIDs().length - 1); // generate a number to pick out winner

		System.out.println(twit.showUser(stat.getIDs()[pick]).getScreenName()); // pick from array list of IDs using
																				// random number
		System.out.println(stat.getIDs().length); // displays amount of people in the array list
	}

	public static void ReplyTweet() throws TwitterException {
		Twitter twt = TwitterFactory.getSingleton();
		Scanner kb = new Scanner(System.in); // scanner
		System.out.println("enter status ID of the tweet you wanna reply to: "); // input status ID of tweet you want to
																					// reply to
		String inReplyToStatusId = kb.nextLine(); // takes in the input

		System.out.println("enter reply: "); // input reply tweet
		String text = kb.nextLine(); // takes in the input

		Status statID = twt.showStatus(Long.parseLong(inReplyToStatusId)); // get status
		String user = statID.getUser().getScreenName(); // get twitter handle of the status owner
		StatusUpdate stat = new StatusUpdate("@" + user + " " + text); // contains the reply to the tweet

		stat.setInReplyToStatusId(Long.parseLong(inReplyToStatusId)); // sets the reply
		twt.updateStatus(stat); // replies
	}
	
	public static String getHTML(String urlToRead) throws Exception {
	      StringBuilder result = new StringBuilder();
	      URL url = new URL(urlToRead);
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setRequestMethod("GET");
	      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	      String line;
	      while ((line = rd.readLine()) != null) {
	         result.append(line);
	      }
	      rd.close();
	      return result.toString();
	   }

}
