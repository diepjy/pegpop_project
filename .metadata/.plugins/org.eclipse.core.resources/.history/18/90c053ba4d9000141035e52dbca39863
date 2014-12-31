package com.pegpop.fragment;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.pegpop.MainPageActivity;
import com.pegpop.R;

import android.support.v4.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Intent;

public class MainFragment extends Fragment{
	
	private static final String TAG = "MainFragment";
	private Session.StatusCallback callback = new Session.StatusCallback() {
	    @Override
	    public void call(Session session, SessionState state, Exception exception) {
	        onSessionStateChange(session, state, exception);
	    }
	};
	private UiLifecycleHelper uiHelper;
//	private final String user_ID = null;
//    private final String profileName = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    uiHelper = new UiLifecycleHelper(getActivity(), callback);
	    uiHelper.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.main_fragment, container, false);
	    LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
	    authButton.setFragment(this);
	    return view;
	}
	
	private void onSessionStateChange(Session session, SessionState state, Exception exception) {
		if (state.isOpened()) {
	        Log.i(TAG, "Logged in1...");
	        /* TODO: If logged in - check the database if they are already a member
	         * 		if(true) go to main page
	         * 		else go to create profile
	         */
			
			Request request = Request.newMeRequest(session, new Request.GraphUserCallback() {
				@Override
				public void onCompleted(GraphUser user, Response response) {
					// If the response is successful
	                    if (user != null) {
	                		String user_ID = null;
	                	    String profileName = null;
	                        user_ID = user.getId();//user id
	                        profileName = user.getName();//user's profile name
//							userNameView.setText(user.getName());
	            	        Log.i(TAG, "The user id is " + user_ID);
	            	        Log.i(TAG, "the user profile name is " + profileName);
	            	        
	            	        verifyFBUser(user_ID);
	                    }   
				}   
	        }); 
	        Request.executeBatchAsync(request);
	        
	        //TODO: If already a member go to main page
			Intent intent = new Intent(getActivity(), MainPageActivity.class);
			startActivity(intent);
			//TODO: If not a member go to sign up page
	    } else if (state.isClosed()) {
	        Log.i(TAG, "Logged out...");
	    }
	}
	
	private void verifyFBUser(String id) {
		//TODO: Change URL
		String urlString = "http://localhost:9000/pegpopDatabase/isMemeber/" + id;
		new CallAPIDatabase().execute(urlString);
	}
	
	@Override
	public void onResume() {
	    super.onResume();
	 // For scenarios where the main activity is launched and user
	    // session is not null, the session state change notification
	    // may not be triggered. Trigger it if it's open/closed.
	    Session session = Session.getActiveSession();
	    if (session != null &&
	           (session.isOpened() || session.isClosed()) ) {
	        onSessionStateChange(session, session.getState(), null);
	    }

	    uiHelper.onResume();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onPause() {
	    super.onPause();
	    uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	    uiHelper.onDestroy();
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    uiHelper.onSaveInstanceState(outState);
	}
	
	private class CallAPIDatabase extends AsyncTask<String, String, String> {
		//String... is String[]
		@Override
		protected String doInBackground(String... params) {
			String urlString=params[0]; // URL to call
			MemberVerification result = null;
		       String resultToDisplay = "";
		 
		       InputStream in = null;
		 
		       // HTTP Get
		       try {
		 
		           URL url = new URL(urlString);
		 
		           HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		 
		           in = new BufferedInputStream(urlConnection.getInputStream());
		 
		        } catch (Exception e ) {
		 
		           System.out.println(e.getMessage());
		 
		           return e.getMessage();
		 
		        }    
		 
		        return resultToDisplay;
		}
		
		protected void onPostExecute(String result) {
			
		}
		
		//Get membership for database
		private MemberVerification getMembership() {
			MemberVerification result = new MemberVerification();
			return result;
		}
		
	}
	
	private class MemberVerification {
		public String pid;
		//They are NOT a member == 0 else if they are a member == 1
		public int isMember = 0;
	}

}
