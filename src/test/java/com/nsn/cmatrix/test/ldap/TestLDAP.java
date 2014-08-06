package com.nsn.cmatrix.test.ldap;

import javax.naming.NamingException;

import org.junit.Assert;
import org.junit.Test;

import com.nsn.cmatrix.exception.AuthenticationException;
import com.nsn.cmatrix.model.ModelMember;
import com.nsn.cmatrix.plugin.ldap.LDAPManager;

public class TestLDAP
{
	private LDAPManager ldapManager = new LDAPManager();
	
	@Test
	public void testGetUserInfo () throws NamingException
	{
		ModelMember member = ldapManager.getUserInfo("x36zhao");
		Assert.assertEquals(member.getUserAccount(), "x36zhao");
		Assert.assertEquals(member.getUserId(), "61344921");
		Assert.assertEquals(member.getUserMail(), "xiang.1.zhao@nsn.com");
	}
	
	@Test
	public void testAuthenticate () throws AuthenticationException
	{
		boolean result = ldapManager.authenticate("x36zhao", "111test");
		Assert.assertEquals(result, false);
	}
}
