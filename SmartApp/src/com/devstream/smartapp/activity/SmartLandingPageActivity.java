package com.devstream.smartapp.activity;

import com.devstream.smartapp.R;
import com.devstream.smartapp.model.Appointment_Service_Option_Model;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SmartLandingPageActivity extends ActionBarActivity implements
		OnClickListener {

	TextView textViewSetting, textViewLook, textViewProvider, textViewUser,
			textViewBook, textViewLOgout, textViewHome, textViewSync;
	Button buttonClinics, buttonVisits;
	LinearLayout llSettings, llLook;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.smart_landingpage);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("View Appointments");
		getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#B2CCFF")));
		initUI();

	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if(id == R.id.home){
			NavUtils.navigateUpFromSameTask(this);
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void initUI(){
		
		buttonClinics = (Button) findViewById(R.id.buttonClinics);
		buttonClinics.setOnClickListener(this);
		
		buttonVisits = (Button) findViewById(R.id.buttonVisits);
		buttonVisits.setOnClickListener(this);
		
		textViewSetting = (TextView) findViewById(R.id.textView_Settings);
		textViewSetting.setOnClickListener(this);
		
		textViewLook = (TextView) findViewById(R.id.textView_Look);
		textViewLook.setOnClickListener(this);
		
		textViewProvider = (TextView) findViewById(R.id.textView_Provider);
		textViewProvider.setOnClickListener(this);
		
		textViewUser = (TextView) findViewById(R.id.textView_User);
		textViewUser.setOnClickListener(this);
		
		textViewBook = (TextView) findViewById(R.id.textView_Book);
		textViewBook.setOnClickListener(this);
		
		textViewLOgout = (TextView) findViewById(R.id.textView_Logout);
		textViewLOgout.setOnClickListener(this);
		
		textViewHome = (TextView) findViewById(R.id.textView_Home);
		textViewHome.setOnClickListener(this);
		
		textViewSync = (TextView) findViewById(R.id.textView_Sync);
		textViewSync.setOnClickListener(this);
		
		llLook = (LinearLayout) findViewById(R.id.layoutLook);
		llSettings = (LinearLayout) findViewById(R.id.layoutSetting);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonClinics:
			intent = new Intent(SmartLandingPageActivity.this, SmartServiceOptionActivity.class);
			startActivity(intent);
			break;
		case R.id.buttonVisits:
			Toast.makeText(getApplicationContext(), "VISITS", Toast.LENGTH_SHORT).show();
			break;
		case R.id.textView_Settings:
			if(llSettings.getVisibility()==View.INVISIBLE){
				llSettings.setVisibility(View.VISIBLE);
				llLook.setVisibility(View.INVISIBLE);
			}else {
				llSettings.setVisibility(View.INVISIBLE);				
			}			
			break;
		case R.id.textView_Look:
			if(llLook.getVisibility()==View.INVISIBLE){
				llLook.setVisibility(View.VISIBLE);
				llSettings.setVisibility(View.INVISIBLE);
			}else {				
				llLook.setVisibility(View.INVISIBLE);
			}
			break;
		case R.id.textView_Home:
			finish();
			break;
		case R.id.textView_Logout:
			finish();
			break;
		case R.id.textView_Sync:
			finish();
			break;
		case R.id.textView_Provider:
			finish();
			break;
		case R.id.textView_User:
			finish();
			break;
		case R.id.textView_Book:
			finish();
			break;
		default:
			break;
		}

	}

}
