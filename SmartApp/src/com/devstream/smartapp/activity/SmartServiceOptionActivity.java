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
import com.devstream.smartapp.model.Appointment_Service_Option_Model;
import com.devstream.smartapp.utility.HttpAuthClazz;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SmartServiceOptionActivity extends ActionBarActivity {

	private static final String TABLE_URL = "http://54.72.7.91:8888/service_options";
	private static final String API_KEY = "6f9a1abf-443e-4d18-a1a8-93dd39f69d6a";
	private String token;
	private URL objectUrl;

	private String name;
	private List<String> listOfServiceOption;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_option);
		getSupportActionBar().setElevation(0);//remove the shadow under actionbar
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("Service Options");
		getSupportActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#B2CCFF")));

		listView = (ListView) findViewById(R.id.listView_ServiceOption);
		new ServiceOptionTask().execute();
		registeredClickCallback();
	}

	/**
	 * 
	 * @author allan inner class extending Asynctask
	 */
	private class ServiceOptionTask extends AsyncTask<String, Void, String> {
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
			listOfServiceOption = new ArrayList<String>();

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
				query = jsonNew.getJSONArray("service_options");

				for (int i = 0; i < query.length(); i++) {
					name = ((JSONObject) query.get(i)).get("name").toString();
					listOfServiceOption.add(name);
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
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					SmartServiceOptionActivity.this,
					R.layout.listview_for_servive_option, listOfServiceOption);

			listView.setAdapter(adapter);
		}
	}

	private void registeredClickCallback() {

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				TextView textView = (TextView) view;

				switch (position) {
				case 0:
					Toast.makeText(SmartServiceOptionActivity.this,
							textView.getText(), 10).show();
					break;
				case 1:
					Toast.makeText(SmartServiceOptionActivity.this,
							textView.getText(), 10).show();
					break;
				case 2:
					Toast.makeText(SmartServiceOptionActivity.this,
							textView.getText(), 10).show();
					break;
				case 3:
					Toast.makeText(SmartServiceOptionActivity.this,
							textView.getText(), 10).show();
					break;
				case 4:
					Toast.makeText(SmartServiceOptionActivity.this,
							textView.getText(), 10).show();
					break;
				default:
					break;
				}

				
			}
		});
	}

}
