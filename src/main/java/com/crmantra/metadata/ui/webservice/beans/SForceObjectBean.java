package com.crmantra.metadata.ui.webservice.beans;

import java.io.Serializable;

import com.sforce.soap.enterprise.DescribeGlobalSObjectResult;

/**
 * response bean for web service call
 * @author ssinha
 */
public class SForceObjectBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String activateable;
	
    private String createable;
    
    private String custom;
    
    private String customSetting;
    
    private String deletable;
    
    private String deprecatedAndHidden;
    
    private String feedEnabled;

    private String label;
    
    private String labelPlural;
    
    private String layoutable;
    
    private String mergeable;
    
    private String name;
    
    private String queryable;
    
    private String replicateable;
    
    private String retrieveable;
    
    private String searchable;
    
    private String triggerable;
    
    private String undeletable;
    
    private String updateable;
    
    /**
     * default constructor<br/>
     * initialize everything as null
     */
    public SForceObjectBean() {
    	this.activateable = null;
    	this.createable = null; 
    	this.custom = null;
    	this.customSetting = null;
    	this.deletable = null;
    	this.deprecatedAndHidden = null; 
    	this.feedEnabled = null;
    	this.label = null;
    	this.labelPlural = null; 
    	this.layoutable = null; 
    	this.mergeable = null; 
    	this.name = null;
    	this.queryable = null; 
    	this.replicateable = null; 
    	this.retrieveable = null; 
    	this.searchable = null; 
    	this.triggerable = null; 
    	this.undeletable = null; 
    	this.updateable = null;
    }
    
    /**
     * create an object using values from {@link DescribeGlobalSObjectResult}
     * @param object {@link DescribeGlobalSObjectResult}
     */
    public SForceObjectBean(DescribeGlobalSObjectResult object) {
    	this.activateable = String.valueOf(object.getActivateable());
    	this.createable = String.valueOf(object.getCreateable()); 
    	this.custom = String.valueOf(object.getCustom());
    	this.customSetting = String.valueOf(object.getCustomSetting());
    	this.deletable = String.valueOf(object.getDeletable());;
    	this.deprecatedAndHidden = String.valueOf(object.getDeprecatedAndHidden());; 
    	this.feedEnabled = String.valueOf(object.getFeedEnabled());;
    	this.label = object.getLabel();
    	this.labelPlural = object.getLabelPlural(); 
    	this.layoutable = String.valueOf(object.getLayoutable());; 
    	this.mergeable = String.valueOf(object.getMergeable());; 
    	this.name = object.getName();
    	this.queryable = String.valueOf(object.getQueryable());; 
    	this.replicateable = String.valueOf(object.getReplicateable());; 
    	this.retrieveable = String.valueOf(object.getRetrieveable());; 
    	this.searchable = String.valueOf(object.getSearchable());; 
    	this.triggerable = String.valueOf(object.getTriggerable());; 
    	this.undeletable = String.valueOf(object.getUndeletable());; 
    	this.updateable = String.valueOf(object.getUpdateable());;
    }
    
    /**
     * create an object with only name
     * @param name {@link String}
     */
    public SForceObjectBean(String name) {
    	this();
    	this.name = name;
    }
    
	public String getActivateable() {
		return activateable;
	}

	public void setActivateable(String activateable) {
		this.activateable = activateable;
	}

	public String getCreateable() {
		return createable;
	}

	public void setCreateable(String createable) {
		this.createable = createable;
	}

	public String getCustom() {
		return custom;
	}

	public void setCustom(String custom) {
		this.custom = custom;
	}

	public String getCustomSetting() {
		return customSetting;
	}

	public void setCustomSetting(String customSetting) {
		this.customSetting = customSetting;
	}

	public String getDeletable() {
		return deletable;
	}

	public void setDeletable(String deletable) {
		this.deletable = deletable;
	}

	public String getDeprecatedAndHidden() {
		return deprecatedAndHidden;
	}

	public void setDeprecatedAndHidden(String deprecatedAndHidden) {
		this.deprecatedAndHidden = deprecatedAndHidden;
	}

	public String getFeedEnabled() {
		return feedEnabled;
	}

	public void setFeedEnabled(String feedEnabled) {
		this.feedEnabled = feedEnabled;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabelPlural() {
		return labelPlural;
	}

	public void setLabelPlural(String labelPlural) {
		this.labelPlural = labelPlural;
	}

	public String getLayoutable() {
		return layoutable;
	}

	public void setLayoutable(String layoutable) {
		this.layoutable = layoutable;
	}

	public String getMergeable() {
		return mergeable;
	}

	public void setMergeable(String mergeable) {
		this.mergeable = mergeable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQueryable() {
		return queryable;
	}

	public void setQueryable(String queryable) {
		this.queryable = queryable;
	}

	public String getReplicateable() {
		return replicateable;
	}

	public void setReplicateable(String replicateable) {
		this.replicateable = replicateable;
	}

	public String getRetrieveable() {
		return retrieveable;
	}

	public void setRetrieveable(String retrieveable) {
		this.retrieveable = retrieveable;
	}

	public String getSearchable() {
		return searchable;
	}

	public void setSearchable(String searchable) {
		this.searchable = searchable;
	}

	public String getTriggerable() {
		return triggerable;
	}

	public void setTriggerable(String triggerable) {
		this.triggerable = triggerable;
	}

	public String getUndeletable() {
		return undeletable;
	}

	public void setUndeletable(String undeletable) {
		this.undeletable = undeletable;
	}

	public String getUpdateable() {
		return updateable;
	}

	public void setUpdateable(String updateable) {
		this.updateable = updateable;
	}
}
