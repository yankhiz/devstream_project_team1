package com.devstream.smartapp.adapter;

import java.util.List;

import com.devstream.smartapp.R;
import com.devstream.smartapp.model.Service_User_Model;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterServiceUser extends BaseAdapter{
	
	private List<Service_User_Model> usersList;
	private Context context;
	
	public AdapterServiceUser(Context context, List<Service_User_Model> usersList) {
		super();
		this.context=context;
		this.usersList=usersList;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return usersList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return usersList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.listview_for_service_user, parent, false);
		
		TextView textViewName = (TextView) view.findViewById(R.id.usersName);
		
		Service_User_Model model = usersList.get(position);
		
		textViewName.setText(model.getmName());
		
		if (position % 2 == 1) {
			view.setBackgroundColor(Color.parseColor("#B2CCFF"));  
		} else {
			view.setBackgroundColor(Color.parseColor("#D1E0FF"));  
		}
		
		return view;
	}

}
