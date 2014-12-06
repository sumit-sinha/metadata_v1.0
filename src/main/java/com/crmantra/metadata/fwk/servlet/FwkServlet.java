package com.crmantra.metadata.fwk.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.crmantra.metadata.fwk.constants.ISForceConstants;

/**
 * a parent class for all the servlets
 * @author ssinha
 */
public class FwkServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected static final Logger logger = Logger.getLogger(FwkServlet.class);
	
	/**
	 * creates a new error object to be send to UI
	 * @param number {@link String} error number
	 * @param message {@link String} message to be displayed
	 * @return {@link String}
	 */
	protected String getErrorMessage(String number, String message) {
		return this.getErrorJSON(number, message).toString();
	}
	
	/**
	 * creates a new error JSON to be send to UI
	 * @param number {@link String} error number
	 * @param message {@link String} message to be displayed
	 * @return {@link String}
	 */
	protected JSONObject getErrorJSON(String number, String message) {
		
		JSONObject error = new JSONObject();
		error.put(ISForceConstants.JSON_KEY_ERROR_NUMBER, number);
		error.put(ISForceConstants.JSON_KEY_ERROR_MESSAGE, message);
		
		return error;
	}
	
	/**
	 * writes the string to response stream
	 * @param response {@link HttpServletResponse}
	 * @param content {@link String}
	 */
	protected void writeToOutputStream(HttpServletResponse response, String content) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json;charset=UTF-8");

		OutputStream os;
		try {
			os = response.getOutputStream();
			os.write(content.getBytes());
			os.flush();
			
			response.flushBuffer();
		} catch (IOException e) {
			if (logger.isTraceEnabled()) {
				logger.trace("FwkServlet: IOException while trying to write response to stream", e);
			}
		}		
	}
}
