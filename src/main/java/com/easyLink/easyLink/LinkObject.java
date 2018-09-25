package com.easyLink.easyLink;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LinkObject{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)   //makes new object Link in database ??
	private String id;
	private String URL;

	public LinkObject() {

	}

	public LinkObject(String id, String URL) {
		this.id = id;
		this.URL = URL;

	}

	public String getId() {
		return id;
	}

	public String getURL() {
		return URL;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setURL(String URL) {
		this.URL = URL;
	}

}