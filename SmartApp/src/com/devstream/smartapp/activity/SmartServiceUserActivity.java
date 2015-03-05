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

import com.devstream.smartapp.R;
import com.devstream.smartapp.adapter.AdapterServiceUser;
import com.devstream.smartapp.model.Service_User_Model;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class SmartServiceUserActivity extends ActionBarActivity {

	private static final String TABLE_URL = "http://54.72.7.91:8888/service_users";
	private static final String API_KEY = "6f9a1abf-443e-4d18-a1a8-93dd39f69d6a";
	private String token;
	private URL objectUrl;

	private AdapterServiceUser adapterServiceUser;
	private ListView listView;
	private LinearLayout layoutServiceUser;
	private ScrollView scrollViewLayout;
	private TextView tv_hospitalNumber, tv_Email, tv_MobilePhone, tv_HomePhone,
			tv_HomeType, tv_Address, tv_HomePostCode, tv_HomeCounty,
			tv_Directions, tv_NextOfKinPhone, tv_NextOfKinName;

	private ArrayList<Service_User_Model> usersList;
	private Service_User_Model service_User;

	private String name;
	private int userId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_user);

		getSupportActionBar().setElevation(0);// remove the shadow under
		// actionbar
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("Service Users");
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#B2CCFF")));

		initUI();
		listView = (ListView) findViewById(R.id.lv_listOfServiceUser);
		scrollViewLayout = (ScrollView) findViewById(R.id.layoutPersonal);

		new FetchServiceUsersTask().execute();
		registerOnClick();

	}

	private void initUI() {
		listView = (ListView) findViewById(R.id.lv_listOfServiceUser);
		layoutServiceUser = (LinearLayout) findViewById(R.id.layout_serviceUser);
		scrollViewLayout = (ScrollView) findViewById(R.id.layoutPersonal);

		tv_hospitalNumber = (TextView) findViewById(R.id.textViewHospitalNumber);
	}

	/**
	 * Sort the output of data by name
	 */
	public void sortData() {
		Collections.sort(usersList, new Comparator<Service_User_Model>() {

			@Override
			public int compare(Service_User_Model lhs, Service_User_Model rhs) {
				// TODO Auto-generated method stub
				return lhs.getmName().compareTo(rhs.getmName());
			}
		});
	}

	/**
	 * 
	 * @author allan inner class extending Asynctask
	 */
	private class FetchServiceUsersTask extends AsyncTask<String, Void, String> {
		HttpURLConnection con;

		@Override
		protected void onPreExecute() {
			usersList = new ArrayList<Service_User_Model>();
			SharedPreferences preferences = getSharedPreferences("credential",
					Context.MODE_PRIVATE);
			token = preferences.getString("authToken", "");

		}

		@Override
		protected String doInBackground(String... params) {
			Log.d("SmartServiceUsersAsynctask", "doInbackground called");
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
				JSONArray query = jsonNew.getJSONArray("service_users");

				for (int i = 0; i < query.length(); i++) {

					// userId = ((JSONObject) query.get(i)).getInt("id");
					name = ((JSONObject) ((JSONObject) query.get(i))
							.get("personal_fields")).get("name").toString();

					service_User = new Service_User_Model(name);
					usersList.add(service_User);
				}

				/*
				 * String []test = {"dfgh","fghj","rtyujn"}; for(int i=0;
				 * i<test.length;i++){ name = test[i]; service_User = new
				 * Service_User_Model(name); usersList.add(service_User); }
				 */

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
			sortData();
			Toast.makeText(SmartServiceUserActivity.this, name, 5).show();

			Log.d("SmartServiceUsersAsynctask", "postexecute called");
			adapterServiceUser = new AdapterServiceUser(
					SmartServiceUserActivity.this, usersList);
			listView.setAdapter(adapterServiceUser);

		}

	}

	private void registerOnClick() {

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				if (listView.getVisibility() == View.VISIBLE) {
					listView.setVisibility(View.GONE);
					layoutServiceUser.setVisibility(View.VISIBLE);
				}

				service_User = usersList.get(position);

			}
		});
	}

}
