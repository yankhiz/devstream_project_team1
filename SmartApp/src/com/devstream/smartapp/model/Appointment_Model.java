package com.devstream.smartapp.model;

public class Appointment_Model {

	private String date;
	private String id;
	private int service_provider_id;
	private int service_user_id;
	private String priority;
	private String time;
	private String visit_type;
	private String service_option_ids;

	public Appointment_Model() {
		super();
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getService_provider_id() {
		return service_provider_id;
	}

	public void setService_provider_id(int service_provider_id) {
		this.service_provider_id = service_provider_id;
	}

	public int getService_user_id() {
		return service_user_id;
	}

	public void setService_user_id(int service_user_id) {
		this.service_user_id = service_user_id;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getVisit_type() {
		return visit_type;
	}

	public void setVisit_type(String visit_type) {
		this.visit_type = visit_type;
	}

	public String getService_option_ids() {
		return service_option_ids;
	}

	public void setService_option_ids(String service_option_ids) {
		this.service_option_ids = service_option_ids;
	}

}
