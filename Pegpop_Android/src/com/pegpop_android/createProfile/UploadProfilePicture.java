package com.pegpop_android.createProfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.pegpop_android.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UploadProfilePicture extends ActionBarActivity {
	
	private ImageButton nextButton;
	private Button uploadPictureButton;
	
	private final int REQUEST_IMAGE_CAPTURE = 1;
//	private final int REQUEST_CAMERA = 1;
	private final int SELECT_FILE = 2;
	
	private String TAG = "UploadProfilePicture";
	
	private ImageView mImageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_profile);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setCustomView(R.layout.actionbar_create_profile);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME
				| ActionBar.DISPLAY_SHOW_CUSTOM); 
		setCustomTitle("Create Profile");
		
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xff78CACA));

		nextButton = (ImageButton) findViewById(R.id.next);
		nextButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent i = new Intent(getApplication(), TermsAndConditions.class);
				startActivity(i);
			}
		});
	}

	private void setCustomTitle(String title) {
		TextView textViewTitle = (TextView) findViewById(R.id.profilePitcureTitle);
		textViewTitle.setText(title);
	}
	
	public void selectPicture(View view) {
		Log.i(TAG, "upload picture button pressed"); 
		selectPicture();
	}
	
	private void selectPicture() {
		final CharSequence[] items = {"Take photo", "Gallery", "Facebook", "Instagram"};
		AlertDialog.Builder builder = new AlertDialog.Builder(UploadProfilePicture.this);
		builder.setItems(items, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int item) {
				if(items[item].equals("Take photo")) {
					dispatchTakePictureIntent();
				}
				if(items[item].equals("Gallery")) {
					Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
					photoPickerIntent.setType("image/*");
					startActivityForResult(photoPickerIntent, SELECT_FILE);   
				}
				if(items[item].equals("Facebook")) {
					
				}
				if(items[item].equals("Instagram")) {
					
				}
				dialog.dismiss();
			}
		});
		builder.show();
	}
	
	private void dispatchTakePictureIntent() {
	    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
	        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
	    }
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode == RESULT_OK) {
			switch(requestCode) {
			case REQUEST_IMAGE_CAPTURE:
		        Bundle extras = data.getExtras();
		        Bitmap imageBitmap = (Bitmap) extras.get("data");
		        mImageView = (ImageView) findViewById(R.id.profilePicture); 
		        mImageView.setImageBitmap(imageBitmap);
		        break;
			case SELECT_FILE:
				Uri selectedImage = data.getData();
	            String[] filePathColumn = {MediaStore.Images.Media.DATA};

	            Cursor cursor = getContentResolver().query(
	                               selectedImage, filePathColumn, null, null, null);
	            cursor.moveToFirst();

	            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
	            String filePath = cursor.getString(columnIndex);
	            cursor.close();


	            Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePath);
		        mImageView = (ImageView) findViewById(R.id.profilePicture); 
//		        mImageView.setImageBitmap(yourSelectedImage);
		        
		        final BitmapFactory.Options options = new BitmapFactory.Options();
		        options.inSampleSize = 8;

		        Bitmap bm = BitmapFactory.decodeFile(filePath,options);
		        mImageView.setImageBitmap(bm);
	            
//	            BitmapFactory.Options options = new BitmapFactory.Options();
//	            options.inJustDecodeBounds = true;
//	            mImageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.id.profilePicture, options));
//	            BitmapFactory.decodeResource(getResources(), R.id.profilePicture, options);

//	            mImageView.setImageBitmap(
//	            	    decodeSampledBitmapFromResource(getResources(), R.id.profilePicture, 100, 100));
				break;
		    
			}
		}
	}
	
	public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
    // Raw height and width of image
    final int height = options.outHeight;
    final int width = options.outWidth;
    int inSampleSize = 1;

    if (height > reqHeight || width > reqWidth) {

        final int halfHeight = height / 2;
        final int halfWidth = width / 2;

        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
        // height and width larger than the requested height and width.
        while ((halfHeight / inSampleSize) > reqHeight
                && (halfWidth / inSampleSize) > reqWidth) {
            inSampleSize *= 2;
        }
    }

    return inSampleSize;
}
	
	public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
	        int reqWidth, int reqHeight) {

	    // First decode with inJustDecodeBounds=true to check dimensions
	    final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    BitmapFactory.decodeResource(res, resId, options);

	    // Calculate inSampleSize
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

	    // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;
	    return BitmapFactory.decodeResource(res, resId, options);
	}

}
