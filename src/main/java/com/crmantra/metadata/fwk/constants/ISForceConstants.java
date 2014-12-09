package com.crmantra.metadata.fwk.constants;

/**
 * list of constants
 * @author ssinha
 */
public interface ISForceConstants {
	
	/**
	 * constant which defines the URL to target <br/>
	 * URL should be updated based on API version targeted
	 */
	public String AUTHORIZATION_URL = "https://login.salesforce.com/services/Soap/c/32.0/";
	
	/**
	 * constant which defines the parameter name for username field
	 */
	public String UI_PARAM_USERNAME = "USER_NAME";
	
	/**
	 * constant which defines the parameter name for password field
	 */
	public String UI_PARAM_USERPASSWORD = "USER_PASSWORD";
	
	/**
	 * constant which defines the parameter name for object field
	 */
	public String UI_PARAM_OBJECTNAME = "OBJECT_NAME";
	
	/**
	 * constant which defines the parameter name for object info flag
	 */
	public String UI_PARAM_FULL_INFO = "FULL_OBJECT_INFO";
	
	/**
	 * constant which defines the parameter name for object description flag
	 */
	public String UI_PARAM_FULL_DESCRIPTION = "FULL_OBJECT_DESCRIPTION";
	
	/**
	 * key for variable "name" in JSON
	 */
	public String JSON_KEY_OBJECT_NAME = "name";
	
	/**
	 * key for variable "list_object" in JSON
	 */
	public String JSON_KEY_LIST_OBJECTS = "list_objects";
	
	/**
	 * key for variable "number" in JSON
	 */
	public String JSON_KEY_ERROR_NUMBER = "number";
	
	/**
	 * key for variable "message" in JSON
	 */
	public String JSON_KEY_ERROR_MESSAGE = "message";
	
	/**
	 * error number 90001
	 */
	public String ERROR_NUMBER_90001 = "90001";
	
	/**
	 * error number 90002
	 */
	public String ERROR_NUMBER_90002 = "90002";
	
	/**
	 * error number 90003
	 */
	public String ERROR_NUMBER_90003 = "90003";
	
	/**
	 * error number 90004
	 */
	public String ERROR_NUMBER_90004 = "90004";
	
	/**
	 * used to maintain object in session
	 */
	public String ENTP_CONNECTION = "ENTP_CONNECTION";
}
