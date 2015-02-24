package com.devstream.smartapp.model;

public class Clinic_Model {

	private int clinicId;
	private String name;
	private String address;
	private String openingTime;
	private String closingTime;
	private String recurrence;
	private String type;
	private String day ;
	private int serviceOptionId;
	private String appointments;
	private String announcements;
	private final static int APPOINTMENT_INTERVAL = 15;

	public Clinic_Model() {
		super();
	}

	public Clinic_Model(String name, String recurrence,String day, String address) {
		this.name=name;
		this.recurrence=recurrence;
		this.day=day;
		this.address=address;
	}

	public int getClinicId() {
		return clinicId;
	}

	public void setClinicId(int clinicId) {
		this.clinicId = clinicId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOpeningTime() {
		return openingTime;
	}

	public void setOpeningTime(String openingTime) {
		this.openingTime = openingTime;
	}

	public String getClosingTime() {
		return closingTime;
	}

	public void setClosingTime(String closingTime) {
		this.closingTime = closingTime;
	}

	public String getRecurrence() {
		return recurrence;
	}

	public void setRecurrence(String recurrence) {
		this.recurrence = recurrence;
	}

	public int getAppointmentInterval() {
		return APPOINTMENT_INTERVAL;
	}

	public String getDay() {
		return day;
	}

	public int getServiceOptionId() {
		return serviceOptionId;
	}

	public void setServiceOptionId(int serviceOptionId) {
		this.serviceOptionId = serviceOptionId;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getAppointments() {
		return appointments;
	}

	public void setAppointments(String appointments) {
		this.appointments = appointments;
	}

	public String getAnnouncements() {
		return announcements;
	}

	public void setAnnouncements(String announcements) {
		this.announcements = announcements;
	}

}
