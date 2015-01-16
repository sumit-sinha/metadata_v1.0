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
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof MetadataServiceInputBean) {
			MetadataServiceInputBean bean = (MetadataServiceInputBean)obj;
			if (bean.isSendObjectInfo() == this.isSendObjectInfo() 
					&& ((this.getUsers() != null && bean.getUsers() != null && this.getUsers().length == bean.getUsers().length) 
							|| (this.getUsers() == null && bean.getUsers() == null))) {
				for (int i = 0; i < this.getUsers().length; i++) {
					if (!this.getUsers()[i].equals(bean.getUsers()[i])) {
						return false;
					}
				}
				
				return true;
			}
		}
		
		return false;
	}
}
