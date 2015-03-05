package com.devstream.smartapp.adapter;

import java.util.List;

import com.devstream.smartapp.R;
import com.devstream.smartapp.model.Service_Provider_Model;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterServiceProvider extends BaseAdapter{
	
	private List<Service_Provider_Model> providersList;
	private Context context;
	
	public AdapterServiceProvider(Context context, List<Service_Provider_Model> providersList) {
		super();
		this.context=context;
		this.providersList=providersList;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return providersList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return providersList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.service_plist_layout, parent, false);
		
		TextView textViewName = (TextView) view.findViewById(R.id.providersName);
		TextView textViewPhone = (TextView) view.findViewById(R.id.providersPhone);
		
		Service_Provider_Model model = providersList.get(position);
		
		textViewName.setText(model.getName());
		textViewPhone.setText(model.getPrimaryPhone());
		
		if (position % 2 == 1) {
			view.setBackgroundColor(Color.parseColor("#B2CCFF"));  
		} else {
			view.setBackgroundColor(Color.parseColor("#D1E0FF"));  
		}
		
		return view;
	}

}
