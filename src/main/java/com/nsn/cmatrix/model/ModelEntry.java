package com.nsn.cmatrix.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="cm_entry")
public class ModelEntry extends ModelBase
{
	private static final long serialVersionUID = 5002827550302322510L;
	
	public ModelEntry()
	{
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "source_system", length = 10)
	private String sourceSystem;
	
	@Column(name = "source_port", length = 10)
	private String sourcePort;
	
	@Column(name = "dest_system", length = 10)
	private String destSystem;
	
	@Column(name = "dest_port", length = 10)
	private String destPort;
	
	@Column(name = "owner", length = 10)
	private String owner;
	
	@Column(name = "project", length = 10)
	private String project;
	
	@Column(name = "creation_time", length = 32)
	private Date creationTime;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSourceSystem() {
		return sourceSystem;
	}

	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	public String getSourcePort() {
		return sourcePort;
	}

	public void setSourcePort(String sourcePort) {
		this.sourcePort = sourcePort;
	}

	public String getDestSystem() {
		return destSystem;
	}

	public void setDestSystem(String destSystem) {
		this.destSystem = destSystem;
	}

	public String getDestPort() {
		return destPort;
	}

	public void setDestPort(String destPort) {
		this.destPort = destPort;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
}
