package com.devstream.smartapp.model;

public class Announcement_Model {

	private String id;
	private String note;
	private String date;
	private String blocking;

	public Announcement_Model() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBlocking() {
		return blocking;
	}

	public void setBlocking(String blocking) {
		this.blocking = blocking;
	}

}
