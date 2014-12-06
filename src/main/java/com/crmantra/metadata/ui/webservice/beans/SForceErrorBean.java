package com.crmantra.metadata.ui.webservice.beans;

import java.io.Serializable;

/**
 * holds any error information
 * @author ssinha
 */
public class SForceErrorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String number;
	
	private String message;
	
	private String type;
	
	private SForceErrorBean[] suberrors;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public SForceErrorBean[] getSuberrors() {
		return suberrors;
	}

	public void setSuberrors(SForceErrorBean[] suberrors) {
		this.suberrors = suberrors;
	}
}
