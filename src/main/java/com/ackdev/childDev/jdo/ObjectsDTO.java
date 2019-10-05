package com.ackdev.childDev.jdo;

import com.ackdev.common.utility.MyUtility;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.appengine.api.datastore.Text;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.OnLoad;
import com.googlecode.objectify.annotation.OnSave;
import com.googlecode.objectify.annotation.Unindex;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonIgnoreProperties(ignoreUnknown = true)

@Entity
@Unindex
@Cache
@JsonAutoDetect
@ApiModel(value = "Goal Setup", description = "Point in time goal entry")
public class ObjectsDTO {
	@Index
	@Id
	@ApiModelProperty(value = "Unique identifier", allowableValues = "guid")
	public String guid;
	
	public String nodeType;
	public String parent_id;
	public String related_id;
	public String relationshipType;
	public String name;
	public String description;
	public Text jsonData;
	@Index
    public Long actionDate;
    public Long effectiveDate;
    public Long expirationdate;
    public Long enteredDate;
    public Long changedDate;
    
	void onLoad() {
	}

	@OnSave
	void onPersist() { /* do something before persisting */
		changedDate = System.currentTimeMillis();
		if (guid == null)
			guid = MyUtility.makeUUID();

	}

}
