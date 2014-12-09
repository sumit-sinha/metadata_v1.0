package com.crmantra.metadata.ui.webservice.beans;

import java.io.Serializable;

/**
 * bean to define input for the web service
 * @author ssinha
 */
public class MetadataServiceInputBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private UserdataBean[] users;
	
	private boolean sendObjectInfo;

	public UserdataBean[] getUsers() {
		return users;
	}

	public void setUsers(UserdataBean[] users) {
		this.users = users;
	}

	public boolean isSendObjectInfo() {
		return sendObjectInfo;
	}

	public void setSendObjectInfo(boolean sendObjectInfo) {
		this.sendObjectInfo = sendObjectInfo;
	}
}
