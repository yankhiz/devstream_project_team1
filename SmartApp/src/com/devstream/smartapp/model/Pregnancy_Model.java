package com.devstream.smartapp.model;

public class Pregnancy_Model {

	public Pregnancy_Model() {
		super();
	}

	private int pregnancyId;
	private String estimatedDeliveryDate;
	private String serviceUserId;
	private String additionalInfo;
	private String birthMode;
	private String perineum;
	private String gestation;
	private String antiD;
	private String feeding;
	private String lastMenstrualPeriod;
	private String createdAt;
	private String babies;
	

	public String getEstimatedDeliveryDate() {
		return estimatedDeliveryDate;
	}

	public void setEstimatedDeliveryDate(String estimatedDeliveryDate) {
		this.estimatedDeliveryDate = estimatedDeliveryDate;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getBirthMode() {
		return birthMode;
	}

	public void setBirthMode(String birthMode) {
		this.birthMode = birthMode;
	}

	public String getPerineum() {
		return perineum;
	}

	public void setPerineum(String perineum) {
		this.perineum = perineum;
	}

	public String getGestation() {
		return gestation;
	}

	public void setGestation(String gestation) {
		this.gestation = gestation;
	}

	public String getAntiD() {
		return antiD;
	}

	public void setAntiD(String antiD) {
		this.antiD = antiD;
	}

	public String getFeeding() {
		return feeding;
	}

	public void setFeeding(String feeding) {
		this.feeding = feeding;
	}

	public String getLastMenstrualPeriod() {
		return lastMenstrualPeriod;
	}

	public void setLastMenstrualPeriod(String lastMenstrualPeriod) {
		this.lastMenstrualPeriod = lastMenstrualPeriod;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getBabies() {
		return babies;
	}

	public void setBabies(String babies) {
		this.babies = babies;
	}

	public int getPregnancyId() {
		return pregnancyId;
	}

	public void setPregnancyId(int pregnancyId) {
		this.pregnancyId = pregnancyId;
	}

	public String getServiceUserId() {
		return serviceUserId;
	}

	public void setServiceUserId(String serviceUserId) {
		this.serviceUserId = serviceUserId;
	}


}
