package com.crmantra.metadata.ui.webservice.entry;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.crmantra.metadata.fwk.constants.ISForceConstants;
import com.crmantra.metadata.fwk.utils.SForceLoginUtil;
import com.crmantra.metadata.fwk.utils.SForceObjectInfoUtil;
import com.crmantra.metadata.ui.webservice.beans.MetadataServiceInputBean;
import com.crmantra.metadata.ui.webservice.beans.MetadataServiceOutputBean;
import com.crmantra.metadata.ui.webservice.beans.SForceErrorBean;
import com.crmantra.metadata.ui.webservice.beans.SForceObjectBean;
import com.crmantra.metadata.ui.webservice.beans.SForceOutputBean;
import com.crmantra.metadata.ui.webservice.beans.UserdataBean;
import com.sforce.soap.enterprise.DescribeGlobalResult;
import com.sforce.soap.enterprise.DescribeGlobalSObjectResult;
import com.sforce.soap.enterprise.EnterpriseConnection;

/**
 * a web service exposed to access the Metadata Apis
 * @author ssinha
 */
@WebService
public class MetadataWebService {
	
	/**
	 * web service method to read meta data based on parameters
	 * @param input
	 */
	@WebMethod (action="readMetadata")
	public MetadataServiceOutputBean readMetadata(MetadataServiceInputBean input) {
		
		SForceOutputBean[] outputs = null;
		for (int i = 0; i < input.getUsers().length; i++) {
			
			UserdataBean userData = input.getUsers()[i];
			List<SForceErrorBean> errors = new ArrayList<SForceErrorBean>();
			
			// set user to output
			SForceOutputBean sforceOutput = new SForceOutputBean();
			sforceOutput.setUser(userData);
			
			// login
			DescribeGlobalResult results = null;
			EnterpriseConnection connection = SForceLoginUtil.login(userData.getUsername(), userData.getPassword());
			if (connection != null) {
				
				List<SForceObjectBean> response = null;
				results = SForceObjectInfoUtil.getGlobalResult(connection);
				Set<String> objects = SForceObjectInfoUtil.getSetFromArray(userData.getObjects());
				for (int j = 0; results != null && j < results.getSobjects().length; j++) {
					DescribeGlobalSObjectResult result = results.getSobjects()[j];
					if (result != null && (objects == null || objects.contains(result.getName()))) {
						
						// hold the value temporarily
						// below line will ensure that if name of object is passed then SHOW_FULL_DESCRIPTION is always true
						boolean tempShowDesc = input.isShowAllDescription();
						if (objects != null && objects.contains(result.getName())) {
							input.setShowAllDescription(true);
						}
						
						if (response == null) {
							response = new ArrayList<SForceObjectBean>();
						}
						
						if (input.isShowAllDescription()) {
							response.add(new SForceObjectBean(result));
						} else {
							response.add(new SForceObjectBean(result.getName()));
						}
						
						// reset the value
						input.setShowAllDescription(tempShowDesc);
					}
				}
				
				// set the objects
				if (response != null) {
					
					SForceObjectBean[] sForceArr = new SForceObjectBean[response.size()];
					for (int j = 0; j < response.size(); j++) {
						sForceArr[j] = response.get(j);
					}
					
					sforceOutput.setObjects(sForceArr);
				} else {
					SForceErrorBean error = new SForceErrorBean();
					error.setNumber(ISForceConstants.ERROR_NUMBER_90002);
					error.setMessage("There are no objects associated with this user.");
					
					errors.add(error);
				}
				
			} else {
				// send error from here
				SForceErrorBean error = new SForceErrorBean();
				error.setNumber(ISForceConstants.ERROR_NUMBER_90001);
				error.setMessage("Either user name or password is incorrect. Please try again later");
				
				errors.add(error);
			}
			
			// set all the errors to object
			sforceOutput.setMessages(errors.toArray(new SForceErrorBean[errors.size()]));
			
			if (outputs == null) {
				outputs = new SForceOutputBean[input.getUsers().length];
			}
			
			outputs[i] = sforceOutput;
			SForceLoginUtil.logout(connection);
		}
		
		MetadataServiceOutputBean output = new MetadataServiceOutputBean();
		output.setUsers(outputs);
		
		return output;
	}
}
