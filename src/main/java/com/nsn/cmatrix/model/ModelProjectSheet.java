package com.nsn.cmatrix.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="cm_project_sheet")
public class ModelProjectSheet extends ModelBase
{
	private static final long serialVersionUID = 1604623041469246042L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH }, optional = false) 
	@JoinColumn(name = "project_id") 
	private ModelProject project;
	
	@Column(name = "sheet_name")
	private String sheetName;
	
	@Column(name = "sheet_desc")
	private String sheetDesc;
	
	@Column(name = "sheet_index")
	private int sheetIndex;
	
	@Column(name = "sheet_status")
	private int sheetStatus;
	
	@Column(name = "creation_time")
	private Date creationTime;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public ModelProject getProject()
	{
		return project;
	}

	public void setProject(ModelProject project)
	{
		this.project = project;
	}

	public String getSheetName()
	{
		return sheetName;
	}

	public void setSheetName(String sheetName)
	{
		this.sheetName = sheetName;
	}

	public String getSheetDesc()
	{
		return sheetDesc;
	}

	public void setSheetDesc(String sheetDesc)
	{
		this.sheetDesc = sheetDesc;
	}

	public int getSheetIndex()
	{
		return sheetIndex;
	}

	public void setSheetIndex(int sheetIndex)
	{
		this.sheetIndex = sheetIndex;
	}

	public int getSheetStatus()
	{
		return sheetStatus;
	}

	public void setSheetStatus(int sheetStatus)
	{
		this.sheetStatus = sheetStatus;
	}

	public Date getCreationTime()
	{
		return creationTime;
	}

	public void setCreationTime(Date creationTime)
	{
		this.creationTime = creationTime;
	}
	
}
