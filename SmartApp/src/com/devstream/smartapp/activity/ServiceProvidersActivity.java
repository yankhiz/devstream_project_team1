package com.devstream.smartapp.activity;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.devstream.smartapp.R;

public class ServiceProvidersActivity extends ActionBarActivity{
	
	ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_providers);
		lv = (ListView)findViewById(R.id.lv_listOfProviders);
		
		String[] values = new String[] { "Service Providers", 
                "Name",
                "Number"
            
               };
		
		// Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
          android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        lv.setAdapter(adapter); 
	}
	
	

}
