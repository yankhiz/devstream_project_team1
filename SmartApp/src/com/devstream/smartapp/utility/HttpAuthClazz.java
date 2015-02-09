package com.devstream.smartapp.utility;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

public class HttpAuthClazz {

	private HttpURLConnection httpcon;
	private static final String LOG_IN_URL = "http://54.72.7.91:8888/login";

	public String getTheAuthKey() {
		try {
			httpcon = (HttpURLConnection) ((new URL(LOG_IN_URL).openConnection()));
			URLEncoder.encode(LOG_IN_URL, "UTF-8");
			httpcon.setDoOutput(true);
			httpcon.setRequestProperty("Content-Type", "application/json");
			httpcon.setRequestProperty("Accept", "application/json");
			httpcon.setRequestMethod("POST");
			httpcon.connect();

			//HashMap<String, String> credentials = new HashMap<String, String>();
			JSONObject jsonLogin = new JSONObject();
			JSONObject credentials = new JSONObject();
			credentials.put("username", "team_andorra");
			credentials.put("password", "smartappiscoming");
			jsonLogin.put("login", credentials);

			// form request
			byte[] inputBytes = jsonLogin.toString().getBytes("UTF-8");
			// "{\"login\":{\"username\":\"team_andorra\",\"password\":\"smartappiscoming\"}}"

			OutputStream os = httpcon.getOutputStream();
			os.write(inputBytes);
			os.close();
			
			// grab the response
			InputStream is = httpcon.getInputStream();
			int ch;
			StringBuffer sb = new StringBuffer();
			while ((ch = is.read()) != -1) {
				sb.append((char) ch);
			}
			
			// create JSON Object to get Token using token key
			JSONObject json = new JSONObject(sb.toString());

			String strToken = (String) ((JSONObject) json.get("login"))
					.get("token");
			httpcon.disconnect();
			return strToken;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
