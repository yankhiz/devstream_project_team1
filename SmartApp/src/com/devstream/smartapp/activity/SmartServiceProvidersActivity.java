package com.devstream.smartapp.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.devstream.smartapp.R;
import com.devstream.smartapp.R.layout;
import com.devstream.smartapp.adapter.AdapterServiceProvider;
import com.devstream.smartapp.model.Clinic_Model;
import com.devstream.smartapp.model.Service_Provider_Model;
import com.devstream.smartapp.utility.HttpAuthClazz;

public class SmartServiceProvidersActivity extends ActionBarActivity {

	private static final String TABLE_URL = "http://54.72.7.91:8888/service_providers";
	private static final String API_KEY = "6f9a1abf-443e-4d18-a1a8-93dd39f69d6a";
	private String token;
	private URL objectUrl;

	private ListView listView;
	private LinearLayout layout;
	private AdapterServiceProvider adapterServiceProvider;
	private TextView tv_name, tv_userName, tv_email, tv_primayPhone,
			tv_secondaryPhone, tv_adminAccess, tv_active, tv_occupation,
			tv_jobLevel;
	
	private ArrayList<Service_Provider_Model> providersList;
	private Service_Provider_Model serviceProvider;

	private String name, username, email, password, jobOccupation, jobLevel,
			primaryPhone, secondaryPhone;
	private boolean active, admin;
	private int id;
	
	private ProgressDialog progressDialog;


	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_providers);

		getSupportActionBar().setElevation(0);// remove the shadow under
												// actionbar
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("Service providers");
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#B2CCFF")));

		listView = (ListView) findViewById(R.id.lv_listOfProviders);
		layout = (LinearLayout) findViewById(R.id.layout_userPovider);
		tv_name = (TextView) findViewById(R.id.textViewProvidersName);
		tv_userName = (TextView) findViewById(R.id._userName);
		tv_email = (TextView) findViewById(R.id._email);
		tv_primayPhone = (TextView) findViewById(R.id._primaryPhone);
		tv_secondaryPhone = (TextView) findViewById(R.id._secondaryPhone);
		tv_adminAccess = (TextView) findViewById(R.id._adminAccess);
		tv_active = (TextView) findViewById(R.id._active);
		tv_occupation = (TextView) findViewById(R.id._occupation);
		tv_jobLevel = (TextView) findViewById(R.id._jobLevel);

		new FetchServiceProvidersTask().execute();
		registerOnClick();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.smart_service_provider, menu);
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
	 * Sort the output of data
	 */
	public void sortData() {
		Collections.sort(providersList,
				new Comparator<Service_Provider_Model>() {

					@Override
					public int compare(Service_Provider_Model lhs,
							Service_Provider_Model rhs) {
						// TODO Auto-generated method stub
						return lhs.getName().compareTo(rhs.getName());
					}
				});
	}

	/**
	 * 
	 * @author allan inner class extending Asynctask
	 */
	private class FetchServiceProvidersTask extends
			AsyncTask<String, Void, String> {
		HttpURLConnection con;

		@Override
		protected void onPreExecute() {
			
			progressDialog = new ProgressDialog(SmartServiceProvidersActivity.this);
			//progressDialog.setMessage("Connecting to server....");
			progressDialog.setCancelable(false);
			progressDialog.setIndeterminate(true);
			progressDialog.show();
			
			providersList = new ArrayList<Service_Provider_Model>();
			SharedPreferences preferences = getSharedPreferences("credential",
					Context.MODE_PRIVATE);
			token = preferences.getString("authToken", "");

		}

		@Override
		protected String doInBackground(String... params) {
			Log.d("SmartServiceProvidersAsynctask", "doInbackground called");
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
				JSONObject jsonNew = new JSONObject(responseString);
				JSONArray query = jsonNew.getJSONArray("service_providers");

				for (int i = 0; i < query.length(); i++) {
					id = ((JSONObject) query.get(i)).getInt("id");
					name = ((JSONObject) query.get(i)).get("name").toString();
					username = ((JSONObject) query.get(i)).get("username")
							.toString();
					email = ((JSONObject) query.get(i)).get("email").toString();
					active = ((JSONObject) query.get(i)).getBoolean("active");
					password = ((JSONObject) query.get(i)).get("password")
							.toString();
					admin = ((JSONObject) query.get(i)).getBoolean("admin");
					jobOccupation = ((JSONObject) query.get(i)).get(
							"job_occupation").toString();
					jobLevel = ((JSONObject) query.get(i)).get("job_level")
							.toString();
					primaryPhone = ((JSONObject) query.get(i)).get(
							"primary_phone").toString();
					secondaryPhone = ((JSONObject) query.get(i)).get(
							"secondary_phone").toString();

					providersList.add(new Service_Provider_Model(id, name,
							username, email, active, password, admin,
							jobOccupation, jobLevel, primaryPhone,
							secondaryPhone));
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
			
			if(progressDialog != null && progressDialog.isShowing()){
				progressDialog.dismiss();
			}
			
			sortData();

			Log.d("SmartServiceProvidersAsynctask", "postexecute called");
			adapterServiceProvider = new AdapterServiceProvider(
					SmartServiceProvidersActivity.this, providersList);
			listView.setAdapter(adapterServiceProvider);

		}

	}

	private void registerOnClick() {

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				if (listView.getVisibility() == View.VISIBLE) {
					listView.setVisibility(View.GONE);
					layout.setVisibility(View.VISIBLE);
				}

				serviceProvider = providersList.get(position);

				tv_name.setText(serviceProvider.getName());
				tv_userName.setText(serviceProvider.getUsername());
				tv_email.setText(serviceProvider.getEmail());
				tv_primayPhone.setText(serviceProvider.getPrimaryPhone());
				tv_secondaryPhone.setText(serviceProvider.getSecondaryPhone());
				tv_adminAccess.setText(String.valueOf(serviceProvider
						.getAdmin()));
				tv_active.setText(String.valueOf(serviceProvider.getActive()));
				tv_occupation.setText(serviceProvider.getJobOccupation());
				tv_jobLevel.setText(serviceProvider.getJobLevel());

			}
		});
	}

	public void back(View view) {
		layout.setVisibility(View.GONE);
		listView.setVisibility(View.VISIBLE);
	}

	public void call(View view) {
		Toast.makeText(SmartServiceProvidersActivity.this,
				"call number " + serviceProvider.getPrimaryPhone(), 5).show();
	}

	public void editData(View view) {
		Toast.makeText(SmartServiceProvidersActivity.this,
				"edit userName " + serviceProvider.getUsername(), 5).show();
	}

}// end class
