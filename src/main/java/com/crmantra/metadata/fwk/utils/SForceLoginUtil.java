package com.crmantra.metadata.fwk.utils;

import org.apache.log4j.Logger;

import com.crmantra.metadata.fwk.constants.ISForceConstants;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.GetUserInfoResult;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

/**
 * a utility class to trigger login
 * @author ssinha
 */
public final class SForceLoginUtil {
	
	private static final Logger logger = Logger.getLogger(SForceLoginUtil.class);
	
	/**
	 * a utility method to initiate login
	 * @param username {@link String}
	 * @param password {@link String}
	 * @return {@link EnterpriseConnection} if success else null is returned
	 */
	public static final EnterpriseConnection login(String username, String password) {

		try {
			ConnectorConfig config = new ConnectorConfig();
			config.setUsername(username);
			config.setPassword(password);
			
			// log for tracking purpose
			if (logger.isInfoEnabled()) {
				logger.info("AuthEndPoint: " + ISForceConstants.AUTHORIZATION_URL);
			}
			
			config.setAuthEndpoint(ISForceConstants.AUTHORIZATION_URL);
			EnterpriseConnection connection = new EnterpriseConnection(config);
			printUserInfo(connection, config);

			return connection;
		} catch (ConnectionException e) {
			if (logger.isTraceEnabled()) {
				logger.trace("SForceLoginUtil: Connection exception happened for user: " + username, e);
			}
		} 

		return null;
	}
	
	/**
	 * a utility method to initiate logout
	 * @param connection {@link EnterpriseConnection} object
	 * @return true if logged out successfully
	 */
	public final static boolean logout(EnterpriseConnection connection) {
		
		if (connection == null) {
			return false;
		}
		
		try {
			connection.logout();
			if (logger.isInfoEnabled()) {
				logger.info("User: " + connection.getUserInfo().getUserId() + " logged out");
			}
			
			return true;
		} catch (ConnectionException e) {
			if (logger.isTraceEnabled()) {
				logger.trace("SForceLoginUtil: Connection exception occured while trying to logout", e);
			}
		}
		
		return false;
	}
	
	/**
	 * a private method used to track the logged in user
	 * @param connection {@link EnterpriseConnection}
	 * @param config {@link ConnectorConfig}
	 */
	private static void printUserInfo(EnterpriseConnection connection, ConnectorConfig config) {
		try {
			GetUserInfoResult userInfo = connection.getUserInfo();
			
			if (logger.isInfoEnabled()) {
				logger.info("\nLogging in ...\n");
				logger.info("UserID: " + userInfo.getUserId());
				logger.info("User Full Name: " + userInfo.getUserFullName());
				logger.info("User Email: " + userInfo.getUserEmail());
				logger.info("SessionID: " + config.getSessionId());
				logger.info("Auth End Point: " + config.getAuthEndpoint());
				logger.info("Service End Point: " + config.getServiceEndpoint());
			}
			
		} catch (ConnectionException e) {
			if (logger.isTraceEnabled()) {
				logger.trace("SForceLoginUtil: Connection exception while printing user info", e);
			}
		}
	}
	
}
