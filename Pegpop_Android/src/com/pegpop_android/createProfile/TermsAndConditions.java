package com.pegpop_android.createProfile;

import com.pegpop_android.R;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

public class TermsAndConditions extends ActionBarActivity {

	private ImageButton nextButton;
	private boolean agree = false;

	private String TAG = "TermsAndConditions";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.terms_and_conditions);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setCustomView(R.layout.actionbar_t_and_c);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME
				| ActionBar.DISPLAY_SHOW_CUSTOM);
		setCustomTitle("Terms and Conditions");

		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(0xff78CACA));

		nextButton = (ImageButton) findViewById(R.id.next);
		nextButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				if (agree) {
					// Intent i = new Intent(getApplication(),
					// AddFriends.class);
					// startActivity(i);
				} else {
					openAlert(view);
				}
			}
		});

	}

	private void setCustomTitle(String title) {
		TextView textViewTitle = (TextView) findViewById(R.id.termsAndConditions);
		textViewTitle.setText(title);
	}

	private void openAlert(View view) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				TermsAndConditions.this);
		alertDialogBuilder.setTitle(getTitle());
		alertDialogBuilder.setNeutralButton("OK",
				new DialogInterface.OnClickListener() {
			
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();

					}
				});

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();

	}

	public void readTermsAndConditions(View view) {
		Intent i = new Intent(getApplication(), ReadTermsAndConditions.class);
		startActivity(i);
	}

	public void onRadioButtonClicked(View view) {
		boolean checked = ((RadioButton) view).isChecked();
		switch (view.getId()) {
		case R.id.agreeRadioButton:
			if (checked) {
				Log.i(TAG, "agree");
				agree = true;
			}
			break;
		case R.id.disagreeRadioButton:
			if (checked) {
				Log.i(TAG, "disagree");
				agree = false;
			}
			break;
		}
	}

}
