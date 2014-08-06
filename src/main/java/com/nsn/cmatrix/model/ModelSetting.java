package com.nsn.cmatrix.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Model for System Setting
 * 
 * @author Jeccy.Zhao
 *
 */
@Entity(name="cm_setting")
public class ModelSetting extends ModelBase
{
	private static final long serialVersionUID = 6586673570061842141L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "system_key")
	private String systemKey;
	
	@Column(name = "system_value")
	private String systemValue;
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getSystemKey()
	{
		return systemKey;
	}

	public void setSystemKey(String systemKey)
	{
		this.systemKey = systemKey;
	}

	public String getSystemValue()
	{
		return systemValue;
	}

	public void setSystemValue(String systemValue)
	{
		this.systemValue = systemValue;
	}
	
}
