package com.devstream.smartapp.activity;

import java.net.URL;
import java.util.List;

import com.devstream.smartapp.R;
import com.devstream.smartapp.model.Service_Provider_Model;
import com.devstream.smartapp.utility.HttpAuthClazz;

import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

public class SmartMainActivity extends ActionBarActivity {

	EditText editTextUserName, editTextPassword;
	Button buttonLogin;
	TextView textViewAbout;

	private static final int RESPONSE_CODE_CREATED = 201;
	private static final String TABLE_URL = "http://54.72.7.91:8888/service_providers";
	private static final String API_KEY = "6f9a1abf-443e-4d18-a1a8-93dd39f69d6a";
	private String token;
	private URL objectUrl;

	boolean isRegistered;
	private int responseCode;
	private String userName, userPass;
	private List<Service_Provider_Model> listOfProvider;
	private Service_Provider_Model serviceProvider;
	
	private Intent intent;
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smart_main);
		getSupportActionBar().setElevation(0);// remove the shadow under actionbar
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#B2CCFF")));

		editTextUserName = (EditText) findViewById(R.id.editTextUsername);
		editTextPassword = (EditText) findViewById(R.id.editTextPassword);
		textViewAbout = (TextView) findViewById(R.id.textViewAbout);
		buttonLogin = (Button) findViewById(R.id.buttonLogin);	
		
		//only use this code to easy fill in the edittext for easy login  for testing
		//delete this later
		editTextUserName.setText("team_andorra");
		editTextPassword.setText("smartappiscoming");
		
		buttonLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				userName = String.valueOf(editTextUserName.getText());
				userPass = String.valueOf(editTextPassword.getText());
				new LogInTask().execute(userName, userPass);			
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.smart_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * implement this method to hide the softkeyboard when you tap anywhere else
	 * in the screen aside from edittext
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
		return super.onTouchEvent(event);
	}

	
	/**
	 * 
	 * @author allan inner class extending Asynctask
	 */
	private class LogInTask extends AsyncTask<String, Void, Integer> {

		@Override
		protected void onPreExecute() {
			progressDialog = new ProgressDialog(SmartMainActivity.this);
			progressDialog.setMessage("Connecting to server....");
			progressDialog.setCancelable(false);
			progressDialog.setIndeterminate(true);
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressDialog.show();
			intent = new Intent(SmartMainActivity.this,
					ViewAppointmentsActivity.class);
		}

		@Override
		protected Integer doInBackground(String... params) {
			Log.d("asynctask", "doInbackground called");			
			token = HttpAuthClazz.getInstance().getAuthKey(params[0], params[1]);
			responseCode = HttpAuthClazz.getInstance().getResponseCode();			
			return responseCode;
		}

		@Override
		protected void onProgressUpdate(Void... values) {
		}

		@Override
		protected void onPostExecute(Integer result) {			
			SharedPreferences preferences = getSharedPreferences("credential", Context.MODE_PRIVATE);
			Editor editor = preferences.edit();
			editor.putString("authToken", token);
			
			if(progressDialog != null && progressDialog.isShowing()){
				progressDialog.dismiss();
			}
			
			if(result == RESPONSE_CODE_CREATED){
				editor.commit();
				startActivity(intent);
			}else{
				Toast.makeText(SmartMainActivity.this, String.valueOf(result), 5).show();
			}
			
		}
	}

	
}// end class
