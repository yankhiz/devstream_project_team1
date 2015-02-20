package com.devstream.smartapp.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.devstream.smartapp.R;
import com.devstream.smartapp.model.Service_Provider_Model;
import com.devstream.smartapp.utility.HttpAuthClazz;

import android.support.v7.app.ActionBarActivity;
import android.text.InputType;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.input.InputManager;
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

	private static final String TABLE_URL = "http://54.72.7.91:8888/service_providers";
	private static final String API_KEY = "6f9a1abf-443e-4d18-a1a8-93dd39f69d6a";
	private String token;
	private URL objectUrl;

	boolean isRegistered;
	private String userName, userPass;
	private List<Service_Provider_Model> listOfProvider;
	private Service_Provider_Model serviceProvider;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smart_main);
		getSupportActionBar().setElevation(0);// remove the shadow under
												// actionbar
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#B2CCFF")));

		editTextUserName = (EditText) findViewById(R.id.editTextUsername);
		editTextPassword = (EditText) findViewById(R.id.editTextPassword);
		textViewAbout = (TextView) findViewById(R.id.textViewAbout);
		buttonLogin = (Button) findViewById(R.id.buttonLogin);

		new LogInTask().execute();

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
	private class LogInTask extends AsyncTask<String, Void, String> {
		HttpURLConnection con;
		JSONObject jsonNew;
		JSONArray query;

		@Override
		protected void onPreExecute() {

		}

		@Override
		protected String doInBackground(String... params) {
			Log.d("asynctask", "doInbackground called");
			token = HttpAuthClazz.getInstance().getAuthKey();
			listOfProvider = new ArrayList<Service_Provider_Model>();

			try {

				objectUrl = new URL(TABLE_URL);
				con = (HttpURLConnection) objectUrl.openConnection();
				con.setRequestMethod("GET");
				// add request header
				con.setRequestProperty("Api-Key", API_KEY);
				con.setRequestProperty("Auth-Token", token);

				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));

				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				String responseString = response.toString();
				jsonNew = new JSONObject(responseString);
				query = jsonNew.getJSONArray("service_providers");

				for (int i = 0; i < query.length(); i++) {
					userName = ((JSONObject) query.get(i)).get("username")
							.toString();
					userPass = ((JSONObject) query.get(i)).get("password")
							.toString();

					listOfProvider.add(new Service_Provider_Model(userName,
							userPass));
				}

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Void... values) {
		}

		@Override
		protected void onPostExecute(String result) {
			Log.d("allan", "postexecute called");
			buttonLogin.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(SmartMainActivity.this,
							ViewAppointmentsActivity.class);

					isRegistered = false;
					for (Service_Provider_Model model : listOfProvider) {

						if (model.getUsername().equals(
								editTextUserName.getText().toString())) {
							isRegistered = true;
						}
					}

					if (isRegistered == true) {
						startActivity(intent);
					} else {
						// inflate a view for errormessage dialog here
						Toast.makeText(getApplicationContext(),
								"Invalid username", 5).show();
					}
				}
			});
		}
	}

}// end class
