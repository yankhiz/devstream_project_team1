package com.devstream.smartapp.model;

import java.util.Date;

public class Service_User_Model {

	private int mServiceUserId;
	private int mBabyId;
	private int mPregnancyId;
	private String mHospitalNumber;

	// Personal fields
	private String mHomeType;
	private String mHomeAddress;
	private String mHomeCounty;
	private String mHomePostcode;
	private String mDirections;
	private Date mDOB;
	private String mEmail;
	private String mHomePhone;
	private String mMobilePhone;
	private String mName;
	private String mNextOfKinPhone;
	private String mNextOfKinName;

	// Clinical fields
	private String mBloodType;// blood_group
	private double mBMI;
	private String mParity;
	private String mPreviousObstetricHistory;
	private boolean mRhesus;
	
	
	

	public Service_User_Model() {
		super();
	}	
	

	public Service_User_Model(String mName) {
		super();
		this.mName = mName;
	}


	public Service_User_Model(int mServiceUserId, int mBabyId,
			int mPregnancyId, String mHospitalNumber, String mHomeType,
			String mHomeAddress, String mHomeCounty, String mHomePostcode,
			String mDirections, Date mDOB, String mEmail, String mHomePhone,
			String mMobilePhone, String mName, String mNextOfKinPhone,
			String mNextOfKinName, String mBloodType, double mBMI,
			String mParity, String mPreviousObstetricHistory, boolean mRhesus) {
		super();
		this.mServiceUserId = mServiceUserId;
		this.mBabyId = mBabyId;
		this.mPregnancyId = mPregnancyId;
		this.mHospitalNumber = mHospitalNumber;
		this.mHomeType = mHomeType;
		this.mHomeAddress = mHomeAddress;
		this.mHomeCounty = mHomeCounty;
		this.mHomePostcode = mHomePostcode;
		this.mDirections = mDirections;
		this.mDOB = mDOB;
		this.mEmail = mEmail;
		this.mHomePhone = mHomePhone;
		this.mMobilePhone = mMobilePhone;
		this.mName = mName;
		this.mNextOfKinPhone = mNextOfKinPhone;
		this.mNextOfKinName = mNextOfKinName;
		this.mBloodType = mBloodType;
		this.mBMI = mBMI;
		this.mParity = mParity;
		this.mPreviousObstetricHistory = mPreviousObstetricHistory;
		this.mRhesus = mRhesus;
	}



	public int getmServiceUserId() {
		return mServiceUserId;
	}

	public void setmServiceUserId(int mServiceUserId) {
		this.mServiceUserId = mServiceUserId;
	}

	public int getmBabyId() {
		return mBabyId;
	}

	public void setmBabyId(int mBabyId) {
		this.mBabyId = mBabyId;
	}

	public int getmPregnancyId() {
		return mPregnancyId;
	}

	public void setmPregnancyId(int mPregnancyId) {
		this.mPregnancyId = mPregnancyId;
	}

	public String getmHospitalNumber() {
		return mHospitalNumber;
	}

	public void setmHospitalNumber(String mHospitalNumber) {
		this.mHospitalNumber = mHospitalNumber;
	}

	public String getmHomeType() {
		return mHomeType;
	}

	public void setmHomeType(String mHomeType) {
		this.mHomeType = mHomeType;
	}

	public String getmHomeAddress() {
		return mHomeAddress;
	}

	public void setmHomeAddress(String mHomeAddress) {
		this.mHomeAddress = mHomeAddress;
	}

	public String getmHomeCounty() {
		return mHomeCounty;
	}

	public void setmHomeCounty(String mHomeCounty) {
		this.mHomeCounty = mHomeCounty;
	}

	public String getmHomePostcode() {
		return mHomePostcode;
	}

	public void setmHomePostcode(String mHomePostcode) {
		this.mHomePostcode = mHomePostcode;
	}

	public String getmDirections() {
		return mDirections;
	}

	public void setmDirections(String mDirections) {
		this.mDirections = mDirections;
	}

	public Date getmDOB() {
		return mDOB;
	}

	public void setmDOB(Date mDOB) {
		this.mDOB = mDOB;
	}

	public String getmEmail() {
		return mEmail;
	}

	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}

	public String getmHomePhone() {
		return mHomePhone;
	}

	public void setmHomePhone(String mHomePhone) {
		this.mHomePhone = mHomePhone;
	}

	public String getmMobilePhone() {
		return mMobilePhone;
	}

	public void setmMobilePhone(String mMobilePhone) {
		this.mMobilePhone = mMobilePhone;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmNextOfKinPhone() {
		return mNextOfKinPhone;
	}

	public void setmNextOfKinPhone(String mNextOfKinPhone) {
		this.mNextOfKinPhone = mNextOfKinPhone;
	}

	public String getmNextOfKinName() {
		return mNextOfKinName;
	}

	public void setmNextOfKinName(String mNextOfKinName) {
		this.mNextOfKinName = mNextOfKinName;
	}

	public String getmBloodType() {
		return mBloodType;
	}

	public void setmBloodType(String mBloodType) {
		this.mBloodType = mBloodType;
	}

	public double getmBMI() {
		return mBMI;
	}

	public void setmBMI(double mBMI) {
		this.mBMI = mBMI;
	}

	public String getmParity() {
		return mParity;
	}

	public void setmParity(String mParity) {
		this.mParity = mParity;
	}

	public String getmPreviousObstetricHistory() {
		return mPreviousObstetricHistory;
	}

	public void setmPreviousObstetricHistory(String mPreviousObstetricHistory) {
		this.mPreviousObstetricHistory = mPreviousObstetricHistory;
	}

	public boolean ismRhesus() {
		return mRhesus;
	}

	public void setmRhesus(boolean mRhesus) {
		this.mRhesus = mRhesus;
	}

}
