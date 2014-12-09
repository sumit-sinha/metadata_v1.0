package com.crmantra.metadata.ui.webservice.beans;

import java.io.Serializable;

public class SForceObjectInputBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String object;
	
	private boolean sendFullDescription;

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public boolean isSendFullDescription() {
		return sendFullDescription;
	}

	public void setSendFullDescription(boolean sendFullDescription) {
		this.sendFullDescription = sendFullDescription;
	}
}
