package com.devstream.smartapp.model;

public class Baby_Model {

	private int babyId;
	private String hospitalNumber;
	private String name;
	private String gender;
	private String weight;
	private boolean vitaminK;
	private String hearing;
	private String newbornScreeningTest;
	private String birthOutcome;
	private String deliveryDateTime;
	private String pregnancy;
	private String serviceUser;

	public Baby_Model() {
		super();
	}

	
	public int getBabyId() {
		return babyId;
	}


	public void setBabyId(int babyId) {
		this.babyId = babyId;
	}


	public String getHospitalNumber() {
		return hospitalNumber;
	}

	public void setHospitalNumber(String hospitalNumber) {
		this.hospitalNumber = hospitalNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String wight) {
		this.weight = wight;
	}

	public boolean getVitaminK() {
		return vitaminK;
	}

	public void setVitaminK(boolean vitaminK) {
		this.vitaminK = vitaminK;
	}

	public String getHearing() {
		return hearing;
	}

	public void setHearing(String hearing) {
		this.hearing = hearing;
	}

	public String getNewbornScreeningTest() {
		return newbornScreeningTest;
	}

	public void setNewbornScreeningTest(String newbornScreeningTest) {
		this.newbornScreeningTest = newbornScreeningTest;
	}

	public String getBirthOutcome() {
		return birthOutcome;
	}

	public void setBirthOutcome(String birthOutcome) {
		this.birthOutcome = birthOutcome;
	}

	public String getDeliveryDateTime() {
		return deliveryDateTime;
	}

	public void setDeliveryDateTime(String deliveryDateTime) {
		this.deliveryDateTime = deliveryDateTime;
	}

	public String getPregnancy() {
		return pregnancy;
	}

	public void setPregnancy(String pregnancy) {
		this.pregnancy = pregnancy;
	}

	public String getServiceUser() {
		return serviceUser;
	}

	public void setServiceUser(String serviceUser) {
		this.serviceUser = serviceUser;
	}

}
