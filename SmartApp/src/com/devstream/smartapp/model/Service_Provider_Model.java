package com.devstream.smartapp.model;

public class Service_Provider_Model {
	

	

	private int id;
	private String name;
	private String username;
	private String email;
	private boolean active;
	private String password;
	private boolean admin;
	private String jobOccupation;
	private String jobLevel;
	private String primaryPhone;
	private String secondaryPhone;
	
	public Service_Provider_Model(String name, String primaryPhone) {
		this.name = name;
		this.primaryPhone = primaryPhone;
	}
	
	

	public Service_Provider_Model(int id, String name, String username,
			String email, boolean active, String password, boolean admin,
			String jobOccupation, String jobLevel, String primaryPhone,
			String secondaryPhone) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.active = active;
		this.password = password;
		this.admin = admin;
		this.jobOccupation = jobOccupation;
		this.jobLevel = jobLevel;
		this.primaryPhone = primaryPhone;
		this.secondaryPhone = secondaryPhone;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getJobOccupation() {
		return jobOccupation;
	}

	public void setJobOccupation(String jobOccupation) {
		this.jobOccupation = jobOccupation;
	}

	public String getPrimaryPhone() {
		return primaryPhone;
	}

	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	public String getSecondaryPhone() {
		return secondaryPhone;
	}

	public void setSecondaryPhone(String secondaryPhone) {
		this.secondaryPhone = secondaryPhone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJobLevel() {
		return jobLevel;
	}

	public void setJobLevel(String jobLevel) {
		this.jobLevel = jobLevel;
	}
	
	

}
