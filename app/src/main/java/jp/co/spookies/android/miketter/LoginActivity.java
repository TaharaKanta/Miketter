package jp.co.spookies.android.miketter;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;

/**
 * twitterのOAuth認証アクティビティ
 */
public class LoginActivity extends Activity {
	private static final String CALLBACK_URI = "miketter://callback";
	private Twitter twitter = null;
	private RequestToken requestToken = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(getString(R.string.consumer_key),
				getString(R.string.consumer_secret));
		try {
			requestToken = twitter.getOAuthRequestToken(CALLBACK_URI);
		} catch (TwitterException e) {
			finish();
		}
		String authUrl = requestToken.getAuthorizationURL();
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(authUrl));
		startActivity(intent);
	}
