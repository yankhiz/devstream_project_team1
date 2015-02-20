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
import com.devstream.smartapp.adapter.AdapterClinic;
import com.devstream.smartapp.model.Clinic_Model;
import com.devstream.smartapp.utility.HttpAuthClazz;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SmartClinicsActivity extends ActionBarActivity {
	
	
	private static final String TABLE_URL = "http://54.72.7.91:8888/clinics";
	private static final String API_KEY = "6f9a1abf-443e-4d18-a1a8-93dd39f69d6a";
	private String token;
	private URL objectUrl;
	private Intent intent;
	private Clinic_Model clinic_Model;
	
	private ArrayList<Clinic_Model> listOfClinic;
	private ListView listView;
	private TextView textViewClinicName;
	
	private AdapterClinic adapterClinic;
	
	
	private String clinicName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clinics);
		getSupportActionBar().setElevation(0);//remove the shadow under actionbar
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("Clinics");
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#B2CCFF")));
		
		
		listView = (ListView) findViewById(R.id.listView_clinics);
		
		new ClinicTask().execute();
		
		
	}

	/**
	 * 
	 * @author allan inner class extending Asynctask
	 */
	private class ClinicTask extends AsyncTask<String, Void, String> {
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
			listOfClinic = new ArrayList<Clinic_Model>();
			clinic_Model = new Clinic_Model();

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
				query = jsonNew.getJSONArray("clinics");

				
				for (int i = 0; i < query.length(); i++) {
					clinicName = ((JSONObject) query.get(i)).get("name").toString();
					clinic_Model.setName(clinicName);
					listOfClinic.add(new Clinic_Model(clinic_Model.getName()));
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
			adapterClinic = new AdapterClinic(SmartClinicsActivity.this, listOfClinic);

			listView.setAdapter(adapterClinic);
		}
	}
	
	
	
	
	
	
}//end of class
