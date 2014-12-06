package com.crmantra.metadata.ui.webservice.beans;

import java.io.Serializable;

/**
 * defines the output of the web-service
 * @author ssinha
 */
public class MetadataServiceOutputBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private SForceOutputBean[] users;

	public SForceOutputBean[] getUsers() {
		return users;
	}

	public void setUsers(SForceOutputBean[] users) {
		this.users = users;
	}
}
