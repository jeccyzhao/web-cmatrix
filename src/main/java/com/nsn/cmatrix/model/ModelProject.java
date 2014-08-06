package com.nsn.cmatrix.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Model for Project
 * 
 * @author Jeccy.Zhao
 *
 */
@Entity(name="cm_project")
public class ModelProject extends ModelBase
{
	private static final long serialVersionUID = 2714537873619797652L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "project_name")
	private String projectName;
	
	@Column(name = "project_desc")
	private String projectDesc;
	
	@Column(name = "project_status")
	private int projectStatus;
	
	@Column(name = "creation_time")
	private Date creationTime;
	
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH, 
			CascadeType.MERGE, CascadeType.REMOVE }, fetch = FetchType.LAZY) 
	private List<ModelProjectSheet> projectSheets = new ArrayList<ModelProjectSheet>();
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public int getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(int projectStatus) {
		this.projectStatus = projectStatus;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public List<ModelProjectSheet> getProjectSheets() {
		return projectSheets;
	}

	public void setProjectSheets(List<ModelProjectSheet> projectSheets) {
		this.projectSheets = projectSheets;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
