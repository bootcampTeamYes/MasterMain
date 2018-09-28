package com.easyLink.easyLink;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class URL{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)   //makes new object Link in database ??
	private String id;
	private String url;

	public URL() {

	}

	public URL(String id, String url) {
		this.id = id;
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public String getURL() {
		return url;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setURL(String url) {
		this.url = url;
	}

}