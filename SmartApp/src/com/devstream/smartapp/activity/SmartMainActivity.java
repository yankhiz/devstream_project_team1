package com.devstream.smartapp.activity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Statement;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.devstream.smartapp.R;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
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
	
	Connection connection;
	Statement stmt;
	private InputStream is;
	private StringBuilder sb;
	private String result;
	
	private String name, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_main);
        
        editTextUserName = (EditText)findViewById(R.id.editTextUsername);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        textViewAbout = (TextView)findViewById(R.id.textViewAbout);
        buttonLogin = (Button)findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {				
				Intent intent = new Intent(SmartMainActivity.this,SmartLandingPageActivity.class);
				startActivity(intent);
			}
		});
        
        new LongOperation().execute((String[]) null);
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

    
    private class LongOperation extends AsyncTask<String, Void, String> {
    	
    	@Override
		protected void onPostExecute(String result) {
    		Log.d("Allan", "name : "+ name);
			editTextUserName.setText(name);
			editTextPassword.setText(password);
		}
    	
		protected String doInBackground(String... params) {
			try {
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(
						//"http://10.15.1.80/test.php");
						  "http://10.15.1.80/team1/service_providers.php");
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				is = entity.getContent();
				Log.d("log_tag", " Connected to database");
			} catch (Exception e) {
				Log.e("log_tag", "Error in http connection" + e.toString());
			}
			
			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(is, "iso-8859-1"), 8);
				sb = new StringBuilder();
				sb.append(reader.readLine() + "\n");
				String line = "";
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				Log.d("log_tag", " Got this far.." + sb);
				is.close();
				result = sb.toString();
			} catch (Exception e) {
				Log.e("log_tag", "Error converting result " + e.toString());
			}
			try {
				JSONArray jArray = new JSONArray(result);
				for (int i = 0; i < jArray.length(); i++) {
					JSONObject json_data = jArray.getJSONObject(i);
					
//					one = json_data.getString("id");
//					two = json_data.getString("hospital_number");
//					three = json_data.getString("blood_type");
					name = json_data.getString("username");
					password = json_data.getString("password");
				}
			} catch (JSONException e1) {
				Toast.makeText(getBaseContext(), "No Events Found",
						Toast.LENGTH_LONG).show();
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPreExecute() {
			
		}

		@Override
		protected void onProgressUpdate(Void... values) {
		}
	}
}
