package com.devstream.smartapp.adapter;

import java.util.ArrayList;

import com.devstream.smartapp.R;
import com.devstream.smartapp.model.Clinic_Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterClinic extends BaseAdapter {

	private ArrayList<Clinic_Model> clinicList;
	private Context context;

	public AdapterClinic(Context context, ArrayList<Clinic_Model> clinicList) {
		super();
		this.clinicList = clinicList;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return clinicList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return clinicList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View view = inflater.inflate(R.layout.listview_for_clinics, parent,false);
		TextView textViewClinicName = (TextView) view.findViewById(R.id.textViewClinicName);
		
		Clinic_Model clinic_Model = clinicList.get(position);
		textViewClinicName.setText(clinic_Model.getName());
		
		return view;
	}

}
