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

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.devstream.smartapp.R;
import com.devstream.smartapp.model.Service_Provider_Model;
import com.devstream.smartapp.utility.HttpAuthClazz;

public class ServiceProvidersActivity extends ActionBarActivity{
	EditText editTextUserName, editTextPassword;
	ListView lv;
	
	private Service_Provider_Model serviceProvider;
	private static final String TABLE_URL = "http://54.72.7.91:8888/service_providers";
	private static final String API_KEY = "6f9a1abf-443e-4d18-a1a8-93dd39f69d6a";
	private String token;
	private URL objectUrl;
	
	String userName;
	String phoneNumber;
	
	private List<Service_Provider_Model> service_providers = new ArrayList<Service_Provider_Model>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_providers);
		
		populateServiceProviderList();

		new LogInTask().execute();
      
	}
	
	private void populateServiceProviderList() {
		//service_providers.add(new Service_Provider_Model());
		
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
	 * @author allan
	 *inner class extending Asynctask
	 */
	private class LogInTask extends AsyncTask<String, Void, String> {
		HttpURLConnection con;
		@Override
		protected void onPreExecute() {

		}

		@Override
		protected String doInBackground(String... params) {
			Log.d("asynctask", "doInbackground called");
			token = new HttpAuthClazz().getTheAuthKey();
			try {
				
				objectUrl = new URL(TABLE_URL);
				con = (HttpURLConnection) objectUrl.openConnection();
				con.setRequestMethod("GET");
				//add request header
				con.setRequestProperty("Api-Key", API_KEY);
				con.setRequestProperty("Auth-Token", token);
				
				BufferedReader in =  new BufferedReader(new InputStreamReader(con.getInputStream()));
				
				String inputLine;
				StringBuffer response = new StringBuffer();
				
				while((inputLine = in.readLine()) != null){
					response.append(inputLine);
				}
				in.close();
				
				String responseString = response.toString();
				JSONObject jsonNew = new JSONObject(responseString);
				JSONArray query = jsonNew.getJSONArray("service_providers");
				
				for(int i=0; i<query.length(); i++){
					userName = ((JSONObject)query.get(i)).get("username").toString();
					phoneNumber = ((JSONObject)query.get(i)).get("primary_phone").toString();
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
			Log.d("asynctask", "postexecute called");
			
		}

		
	}
	
	

}
