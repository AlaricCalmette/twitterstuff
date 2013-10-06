import org.apache.http.client.HttpClient
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.client.methods.HttpGet
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer
import org.apache.commons.io.IOUtils 
import scala.collection.immutable._
/**
Zach Haitz - CSE 383 Fall 2013
A program using rest that pulls a twitter user's latest tweet

Modified from a tutorial to pull follower ids
from http://www.smartjava.org/content/access-twitter-rest-api-v11-scala-and-java-using-signpost
*/ 
object TwitterPull {
          // tokens from dev.twitter.com 
          // don't steal them please!
	  val AccessToken = "104151556-NlHiauPJVDlU3kWe64juLFTR9avRkJgAeBodQXSu";
	  val AccessSecret = "Fyc1u7j3wJEDtws536EEbU11qBX714npaInhl3S7XY";
	  val ConsumerKey = "zYh0ySN4JEMz7ICvscA";
	  val ConsumerSecret = "rjNseEJ1bG4DLJGFWQgUIeH3dQn6dJeFKOEVJjQXSro";
 
 
  def main(args: Array[String]) {
         // Authorize myself as a valid consumer of this data
	 val consumer = new CommonsHttpOAuthConsumer(ConsumerKey,ConsumerSecret);
	 consumer.setTokenWithSecret(AccessToken, AccessSecret);
 
     // Create an HTTP get request to twitter with a user's id from the command line
     val request = new HttpGet("http://api.twitter.com/1.1/statuses/user_timeline.json?screen_name="+args(0)+"&count=1");
     consumer.sign(request); // sign
     val client = new DefaultHttpClient();
     val response = client.execute(request);
 
     println(response.getStatusLine().getStatusCode())
     println()
     val twitter = IOUtils.toString(response.getEntity().getContent())
     
     
     // was going to try to learn how to hackily parse JSON, but
     // I gave up on that after an hour... So I just print the mess
     // I get back
     //val json = scala.util.parsing.json.JSON.parseFull(twitter)
     println(twitter)
   }
}

