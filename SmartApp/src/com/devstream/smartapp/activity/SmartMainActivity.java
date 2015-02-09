package com.devstream.smartapp.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.devstream.smartapp.R;
import com.devstream.smartapp.model.Service_Provider_Model;
import com.devstream.smartapp.utility.HttpAuthClazz;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
			token = new HttpAuthClazz().getTheAuthKey();
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
							SmartLandingPageActivity.class);

					isRegistered=false;
					for (Service_Provider_Model model : listOfProvider) {
						
						if(model.getUsername().equals(editTextUserName.getText().toString())){
							isRegistered=true;
						}
					}
					
					if(isRegistered == true){
						startActivity(intent);
					}else{
						//inflate a view for errormessage dialog here
						Toast.makeText(getApplicationContext(), "Invalid username", 5).show();
					}
				}
			});
		}
	}

}//end class
