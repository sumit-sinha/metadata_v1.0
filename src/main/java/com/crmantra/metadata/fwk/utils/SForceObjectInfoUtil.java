package com.crmantra.metadata.fwk.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.sforce.soap.enterprise.DescribeGlobalResult;
import com.sforce.soap.enterprise.DescribeSObjectResult;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.ws.ConnectionException;


/**
 * a utility class used to generate soap requests
 * @author ssinha
 */
public final class SForceObjectInfoUtil {
	
	private static final Logger logger = Logger.getLogger(SForceObjectInfoUtil.class);
	
	/**
	 * convert an array of String to Set
	 * @param values array of string
	 * @return Set
	 */
	public static final Set<String> getSetFromArray(String[] values) {
		
		if (values == null) {
			return null;
		}
		
		Set<String> objectNames = new HashSet<String>();
		for (int i = 0; i < values.length; i++) {
			addToSet(objectNames, values[i]);
		}
		
		return objectNames;
	}
	
	/**
	 * convert an list of String to Set
	 * @param values list of string
	 * @return Set
	 */
	public static final Set<String> getSetFromArray(List<String> values) {
		
		Set<String> objectNames = null;
		for (int i = 0; i < values.size(); i++) {
			addToSet(objectNames, values.get(i));
		}
		
		return objectNames;
	}
	
	/**
	 * add a value to set
	 * @param objectNames
	 * @param value
	 */
	private static final void addToSet(Set<String> objectNames, String value) {
		if (objectNames == null) {
			return;
		}
		
		if (!objectNames.contains(value)) {
			objectNames.add(value);
		}
	}
	
	/**
	 * get object description
	 * @param connection {@link EnterpriseConnection}
	 */
	public static final DescribeSObjectResult getObjectDescription(EnterpriseConnection connection, String object) {
		if (connection == null) {
			return null;
		}
		
		try {
			
			String[] objects = new String[1];
			objects[0] = object;
			
			DescribeSObjectResult[] results = connection.describeSObjects(objects);
			if (results != null && results.length > 0) {
				return results[0];
			}
		} catch (ConnectionException e) {
			return null;
		}
		
		return null;
	}
	
	/**
	 * get all the object associated with the user
	 * @param connection {@link EnterpriseConnection}
	 * @return {@link DescribeGlobalResult}
	 */
	public static final DescribeGlobalResult getGlobalResult(EnterpriseConnection connection) {
		
		if (connection == null) {
			return null;
		}
		
		try {
			return connection.describeGlobal();
		} catch (ConnectionException e) {
			if (logger.isTraceEnabled()) {
				logger.trace("SForceObjectInfoUtil: Connection exception happened while trying to fetch all objects", e);
			}
		}
		
		return null;
	}
	
}