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
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.devstream.smartapp.R;
import com.devstream.smartapp.adapter.AdapterServiceUser;
import com.devstream.smartapp.model.Service_User_Model;

import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Context;
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
	private TextView tv_ServiceUserName, tv_hospitalNumber, tv_Email,
			tv_MobilePhone, tv_HomePhone, tv_HomeType, tv_Address,
			tv_HomePostCode, tv_HomeCounty, tv_Directions, tv_NextOfKinPhone,
			tv_NextOfKinName;

	private ArrayList<Service_User_Model> usersList;
	private Service_User_Model service_User;

	private String mName, mHospitalNumber, mEmail, mMobilePhone, mHomePhone,
			mHomeType, mHomeAddress, mHomePostCode, mHomeCounty, mDirections,
			mDOB, mNextOfKinPhone, mNextOfKinName, mBloodType, mParity,
			mPreviousObstetricHistory;
	private int mServiceUserId, mBabyId, mPregnancyId;
	private double mBMI;
	private boolean mRhesus;
	
	private ProgressDialog progressDialog;


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

		tv_ServiceUserName = (TextView) findViewById(R.id.textViewServiceUserName);
		tv_hospitalNumber = (TextView) findViewById(R.id.textViewHospitalNumber);
		tv_Email = (TextView) findViewById(R.id.textViewEmail);

		tv_MobilePhone = (TextView) findViewById(R.id.textViewMobilePhone);
		tv_HomePhone = (TextView) findViewById(R.id.textViewHomePhone);
		tv_HomeType = (TextView) findViewById(R.id.textViewHomeType);
		tv_Address = (TextView) findViewById(R.id.textViewAddress);
		tv_HomePostCode = (TextView) findViewById(R.id.textViewHomePostCode);
		tv_HomeCounty = (TextView) findViewById(R.id.textViewHomeCounty);
		tv_Directions = (TextView) findViewById(R.id.textViewDirections);
		tv_NextOfKinName = (TextView) findViewById(R.id.textViewNextOfKinName);
		tv_NextOfKinPhone = (TextView) findViewById(R.id.textViewNextOfKinPhone);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.smart_service_user, menu);
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
			progressDialog = new ProgressDialog(SmartServiceUserActivity.this);
			//progressDialog.setMessage("Connecting to server....");
			progressDialog.setCancelable(false);
			progressDialog.setIndeterminate(true);
			progressDialog.show();
			
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
				JSONObject jsonObjectServiceUser = new JSONObject(responseString);
				JSONArray jsonArrayServiceUser = jsonObjectServiceUser.getJSONArray("service_users");
				JSONArray jsonArrayBaby = jsonObjectServiceUser.getJSONArray("babies");
				JSONArray jsonArrayMother = jsonObjectServiceUser.getJSONArray("pregnancies");
				

				for (int i = 0; i < jsonArrayServiceUser.length(); i++) {

					mServiceUserId = ((JSONObject) jsonArrayServiceUser.get(i))
							.getInt("id");
					mHospitalNumber = ((JSONObject) jsonArrayServiceUser.get(i))
							.getString("hospital_number");

					mName = ((JSONObject) ((JSONObject) jsonArrayServiceUser
							.get(i)).get("personal_fields")).getString("name");
					mEmail = ((JSONObject) ((JSONObject) jsonArrayServiceUser
							.get(i)).get("personal_fields")).getString("email");
					mMobilePhone = ((JSONObject) ((JSONObject) jsonArrayServiceUser
							.get(i)).get("personal_fields"))
							.getString("mobile_phone");
					mHomePhone = ((JSONObject) ((JSONObject) jsonArrayServiceUser
							.get(i)).get("personal_fields"))
							.getString("home_phone");
					mHomeType = ((JSONObject) ((JSONObject) jsonArrayServiceUser
							.get(i)).get("personal_fields"))
							.getString("home_type");
					mHomeAddress = ((JSONObject) ((JSONObject) jsonArrayServiceUser
							.get(i)).get("personal_fields"))
							.getString("home_address");
					mHomePostCode = ((JSONObject) ((JSONObject) jsonArrayServiceUser
							.get(i)).get("personal_fields"))
							.getString("home_post_code");
					mHomeCounty = ((JSONObject) ((JSONObject) jsonArrayServiceUser
							.get(i)).get("personal_fields"))
							.getString("home_county");
					mDirections = ((JSONObject) ((JSONObject) jsonArrayServiceUser
							.get(i)).get("personal_fields"))
							.getString("directions");
					mNextOfKinPhone = ((JSONObject) ((JSONObject) jsonArrayServiceUser
							.get(i)).get("personal_fields"))
							.getString("next_of_kin_phone");
					mNextOfKinName = ((JSONObject) ((JSONObject) jsonArrayServiceUser
							.get(i)).get("personal_fields"))
							.getString("next_of_kin_name");

					// mServiceUserId
					// mBabyId
					// mPregnancyId
					// mHospitalNumber
					// mHomeType
					// mHomeAddress
					// mHomeCounty
					// mHomePostCode
					// mDirections
					// mDOB
					// mEmail
					// mHomePhone
					// mMobilePhone
					// mName
					// mNextOfKinPhone
					// mNextOfKinName
					// mBloodType
					// mBMI
					// mParity
					// mPreviousObstetricHistory
					// mRhesus

					service_User = new Service_User_Model(mServiceUserId,
							mBabyId, mPregnancyId, mHospitalNumber, mHomeType,
							mHomeAddress, mHomeCounty, mHomePostCode,
							mDirections, mDOB, mEmail, mHomePhone,
							mMobilePhone, mName, mNextOfKinPhone,
							mNextOfKinName, mBloodType, mBMI, mParity,
							mPreviousObstetricHistory, mRhesus);

					usersList.add(service_User);
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

				tv_ServiceUserName.setText(service_User.getmName());
				tv_hospitalNumber.setText(service_User.getmHospitalNumber());
				tv_Email.setText(service_User.getmEmail());
				tv_MobilePhone.setText(service_User.getmMobilePhone());
				tv_HomePhone.setText(service_User.getmHomePhone());
				tv_HomeType.setText(service_User.getmHomeType());
				tv_Address.setText(service_User.getmHomeAddress());
				tv_HomePostCode.setText(service_User.getmHomePostcode());
				tv_HomeCounty.setText(service_User.getmHomeCounty());
				tv_Directions.setText(service_User.getmDirections());
				tv_NextOfKinPhone.setText(service_User.getmNextOfKinPhone());
				tv_NextOfKinName.setText(service_User.getmNextOfKinName());

			}
		});
	}

	public void back(View view) {
		layoutServiceUser.setVisibility(View.GONE);
		listView.setVisibility(View.VISIBLE);
	}

	public void call(View view) {
		Toast.makeText(SmartServiceUserActivity.this,
				"call number " + service_User.getmHomePhone(), 5).show();
	}

	public void editData(View view) {
		Toast.makeText(SmartServiceUserActivity.this,
				"edit userName " + service_User.getmName(), 5).show();
	}

}
