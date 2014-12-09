package com.crmantra.metadata.ui.webservice.beans;

import java.io.Serializable;

import com.sforce.soap.enterprise.DescribeSObjectResult;

/**
 * defines the response
 * @author ssinha
 */
public class SForceMapBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private SForceObjectBean object;
	
	private DescribeSObjectResult result;

	public SForceMapBean(SForceObjectBean object, DescribeSObjectResult result) {
		this.object = object;
		this.result = result;
	}
	
	public SForceObjectBean getObject() {
		return object;
	}

	public void setObject(SForceObjectBean object) {
		this.object = object;
	}

	public DescribeSObjectResult getResult() {
		return result;
	}

	public void setResult(DescribeSObjectResult result) {
		this.result = result;
	}
}
