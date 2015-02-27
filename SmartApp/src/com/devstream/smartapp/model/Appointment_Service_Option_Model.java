package com.devstream.smartapp.model;

public class Appointment_Service_Option_Model {

	

	private int id;
	private String name;

	public Appointment_Service_Option_Model() {
		super();
	}

	public Appointment_Service_Option_Model(String name, int serviceOptionId) {
		this.name=name;
		this.id=serviceOptionId;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
