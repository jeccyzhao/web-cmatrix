package com.nsn.cmatrix.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Model for user
 * 
 * @author Jeccy.Zhao
 *
 */
@Entity(name="cm_member")
public class ModelMember extends ModelBase
{
	private static final long serialVersionUID = -6194389663162156671L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "user_account")
	private String userAccount;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "user_mail")
	private String userMail;
	
	@Column(name = "user_status")
	private int userStatus;
	
	@Column(name = "user_logintime")
	private Date userLastLoginTime;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getUserMail()
	{
		return userMail;
	}

	public void setUserMail(String userMail)
	{
		this.userMail = userMail;
	}

	public int getUserStatus()
	{
		return userStatus;
	}

	public void setUserStatus(int userStatus)
	{
		this.userStatus = userStatus;
	}

	public Date getUserLastLoginTime()
	{
		return userLastLoginTime;
	}

	public void setUserLastLoginTime(Date userLastLoginTime)
	{
		this.userLastLoginTime = userLastLoginTime;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getUserAccount()
	{
		return userAccount;
	}

	public void setUserAccount(String userAccount)
	{
		this.userAccount = userAccount;
	}
	
}
