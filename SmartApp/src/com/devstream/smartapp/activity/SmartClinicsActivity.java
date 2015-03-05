package com.devstream.smartapp.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.devstream.smartapp.R;
import com.devstream.smartapp.adapter.AdapterClinic;
import com.devstream.smartapp.model.Clinic_Model;
import com.devstream.smartapp.utility.HttpAuthClazz;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SmartClinicsActivity extends ActionBarActivity {

	private static final String TABLE_URL = "http://54.72.7.91:8888/clinics";
	private static final String API_KEY = "6f9a1abf-443e-4d18-a1a8-93dd39f69d6a";
	private String token;
	private URL objectUrl;
	private Intent intent;
	private Clinic_Model clinic_Model;
	private Bundle extras;

	private ArrayList<Clinic_Model> listOfClinic;
	private ListView listView;
	private TextView textViewClinicName;

	private AdapterClinic adapterClinic;

	private String address;
	private String clinicName;
	private String recurrence;
	private String day;
	private int serviceOptionIds, SO_Id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clinics);
		getSupportActionBar().setElevation(0);// remove the shadow under
												// actionbar
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("Clinics");
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#B2CCFF")));

		setSO_Id();
		Log.d("mytag", SO_Id + "");

		listView = (ListView) findViewById(R.id.listView_clinics);

		new ClinicTask().execute();
		registerOnClick();

	}

	public void setSO_Id() {
		if (extras == null) {
			extras = getIntent().getExtras();
			SO_Id = extras.getInt("service_option_ids");
		}
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
			SharedPreferences preferences = getSharedPreferences("credential", Context.MODE_PRIVATE);
			token = preferences.getString("authToken", "");

		}

		@Override
		protected String doInBackground(String... params) {
			Log.d("asynctask", "doInbackground called");
			//token = HttpAuthClazz.getInstance().getAuthKey();
			listOfClinic = new ArrayList<Clinic_Model>();
			clinic_Model = new Clinic_Model();
			JSONObject jsonObject;
			String days[] = { "monday", "tuesday", "wednesday", "thursday",
					"friday", "saturday", "sunday" };

			try {

				objectUrl = new URL(TABLE_URL);
				con = (HttpURLConnection) objectUrl.openConnection();
				con.setRequestMethod("GET");
				// add request header
				con.setRequestProperty("Api-Key", API_KEY);
				con.setRequestProperty("Auth-Token", token);

				BufferedReader in = new BufferedReader(new InputStreamReader(
						con.getInputStream()));

				// 54.72.7.91/clinics/{1,2,3,4}

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
					Map<String, Boolean> map = new HashMap<String, Boolean>();
					day = "";
					jsonObject = query.getJSONObject(i).getJSONObject("days");

					// fetching the days
					for (int j = 0; j < days.length; j++) {
						if (jsonObject.getBoolean(days[j]) == true) {
							map.put(days[j], jsonObject.getBoolean(days[j]));
							day += days[j].replace(days[j].charAt(0), days[j]
									.toUpperCase().charAt(0))
									+ "  ";
						}
					}

					// fetching the service_option_ids
					JSONArray arrayOptionId = query.getJSONObject(i)
							.getJSONArray("service_option_ids");
					for (int k = 0; k < arrayOptionId.length(); k++) {
						serviceOptionIds = arrayOptionId.getInt(k);

						if (serviceOptionIds == SO_Id) {
							clinicName = ((JSONObject) query.get(i))
									.get("name").toString();
							recurrence = ((JSONObject) query.get(i)).get(
									"recurrence").toString();
							address = ((JSONObject) query.get(i))
									.get("address").toString();


							listOfClinic.add(new Clinic_Model(clinicName,
									recurrence, day, address));
						}
					}

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
			adapterClinic = new AdapterClinic(SmartClinicsActivity.this,
					listOfClinic);

			listView.setAdapter(adapterClinic);
		}
	}

	private void registerOnClick() {

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Clinic_Model model = listOfClinic.get(position);
				Toast toast = new Toast(getApplicationContext());
				Toast.makeText(SmartClinicsActivity.this,
						"selectedValue" + position + "  " + model.getName(), Toast.LENGTH_SHORT).show();
			}
		});
	}

}// end of class
