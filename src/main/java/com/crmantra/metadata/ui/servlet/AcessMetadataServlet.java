package com.crmantra.metadata.ui.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.crmantra.metadata.fwk.constants.ISForceConstants;
import com.crmantra.metadata.fwk.servlet.FwkServlet;
import com.crmantra.metadata.ui.webservice.beans.MetadataServiceInputBean;
import com.crmantra.metadata.ui.webservice.beans.MetadataServiceOutputBean;
import com.crmantra.metadata.ui.webservice.beans.SForceObjectInputBean;
import com.crmantra.metadata.ui.webservice.beans.UserdataBean;
import com.crmantra.metadata.ui.webservice.entry.MetadataWebService;
/**
 * servlet class used to access Metadata APIs
 * @author ssinha
 */
public class AcessMetadataServlet extends FwkServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.writeToOutputStream(resp, this.getErrorMessage(ISForceConstants.ERROR_NUMBER_90004, "GET is not supported for this action. Please try with POST"));
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.writeToOutputStream(resp, this.getErrorMessage(ISForceConstants.ERROR_NUMBER_90004, "PUT is not supported for this action. Please try with POST"));
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// output array
		MetadataServiceInputBean input = new MetadataServiceInputBean();
		input.setSendObjectInfo(Boolean.parseBoolean(req.getParameter(ISForceConstants.UI_PARAM_FULL_INFO)));
		
		MetadataServiceInputBean storedInput = null;
		if (req.getSession().getAttribute("input") instanceof MetadataServiceInputBean) {
			storedInput = (MetadataServiceInputBean)req.getSession().getAttribute("input");
		}
		
		MetadataServiceOutputBean output = null;	
		if (req.getSession().getAttribute("output") instanceof MetadataServiceOutputBean) {
			output = (MetadataServiceOutputBean)req.getSession().getAttribute("output");
		}
		
		// get parameter from UI
		boolean isLoggedIn = output != null 
				&& input != null 
				&& Boolean.parseBoolean(req.getParameter(ISForceConstants.IS_ALREADY_LOGGED_IN));
		
		// read parameters from request
		List<UserdataBean> users = null;
		for (int i = 1; i > 0; i++) {
			
			String username = null;
			String password = null;
			
			if (!isLoggedIn) {
			
				// to store the data
				StringBuffer userNameKey = new StringBuffer(ISForceConstants.UI_PARAM_USERNAME);
				userNameKey.append("_");
				userNameKey.append(i);
				
				StringBuffer userPwdKey = new StringBuffer(ISForceConstants.UI_PARAM_USERPASSWORD);
				userPwdKey.append("_");
				userPwdKey.append(i);
				
				username = req.getParameter(userNameKey.toString());
				password = req.getParameter(userPwdKey.toString());
			} else {
				if (storedInput.getUsers().length > (i - 1) 
						&& storedInput.getUsers()[i-1] != null) {
					username = storedInput.getUsers()[i-1].getUsername();
					password = storedInput.getUsers()[i-1].fetchPassword();
				}
			}
			
			// exit loop if username or password is not present
			if (username == null || password == null) {
				break;
			}
			
			UserdataBean user = new UserdataBean();
			user.setUsername(username);
			user.setPassword(password);
			
			// get all the object name from param
			List<SForceObjectInputBean> values = null;
			for (int j = 1; j > 0; j++) {
				
				SForceObjectInputBean object = new SForceObjectInputBean();
				
				StringBuffer objectKey = new StringBuffer(ISForceConstants.UI_PARAM_OBJECTNAME);
				objectKey.append("_");
				objectKey.append(i);
				objectKey.append("_");
				objectKey.append(j);
				
				object.setObject(req.getParameter(objectKey.toString()));
				if (object.getObject() == null) {
					break;
				}
				
				StringBuffer objectDescKey = new StringBuffer(ISForceConstants.UI_PARAM_FULL_DESCRIPTION);
				objectDescKey.append("_");
				objectDescKey.append(i);
				objectDescKey.append("_");
				objectDescKey.append(j);
				
				object.setSendFullDescription(Boolean.parseBoolean(req.getParameter(objectDescKey.toString())));
				
				if (values == null) {
					values = new ArrayList<SForceObjectInputBean>();
				}
				
				values.add(object);
			}
			
			if (values != null) {
				// convert list to String array
				SForceObjectInputBean[] objects = new SForceObjectInputBean[values.size()];
				for (int j = 0; j < values.size(); j++) {
					objects[j] = values.get(j);
				}
				
				user.setObjects(objects);
			}
			
			if (users == null) {
				users = new ArrayList<UserdataBean>();
			}
			
			users.add(user);
		}
		
		if (users != null) {
			UserdataBean[] userArr = new UserdataBean[users.size()];
			for (int j = 0; j < users.size(); j++) {
				userArr[j] = users.get(j);
			}
			
			input.setUsers(userArr);
		}
		
		if (storedInput == null || !storedInput.equals(input) || output == null) {

			// read data from service and write to response stream
			output = (new MetadataWebService()).readMetadata(input);
			if (output.getUsers() != null && output.getUsers().length > 0) {
				boolean allLoggedIn = true;
				for (int i = 0; i < output.getUsers().length; i++) {
					if (!output.getUsers()[i].isLoggedId()) {
						allLoggedIn = false;
						break;
					}
				}
				
				if (allLoggedIn) {
					req.getSession().setAttribute("input", input);
					req.getSession().setAttribute("output", output);
				}
			}
		}
		
		this.writeToOutputStream(resp, (new JSONObject(output)).toString());
	}
}
