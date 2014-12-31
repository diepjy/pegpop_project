package com.pegpop_android.createProfile;

import com.pegpop_android.R;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class Username extends ActionBarActivity{
	
private ImageButton nextButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_profile_username);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setCustomView(R.layout.actionbar_username);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME
				| ActionBar.DISPLAY_SHOW_CUSTOM); 
		setCustomTitle("Username");
		
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff78CACA));

		nextButton = (ImageButton) findViewById(R.id.next);
		nextButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent i = new Intent(getApplication(), UploadProfilePicture.class);
				startActivity(i);
			}
		});
	}

	private void setCustomTitle(String title) {
		TextView textViewTitle = (TextView) findViewById(R.id.usernameTitle);
		textViewTitle.setText(title);
	}

}
