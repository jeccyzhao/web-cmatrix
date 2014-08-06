package com.nsn.cmatrix.plugin.ldap;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import com.nsn.cmatrix.exception.AuthenticationException;
import com.nsn.cmatrix.model.ModelMember;

/**
 * The LDAP manager, operations like:
 * Authenticate the intranet user name and password.
 * 
 * @author Jeccy.Zhao
 */
public class LDAPManager
{
	private final static String DEFAULT_LDAPSERVER = "ldap://ed-p-gl.emea.nsn-net.net:389";
	private final static String DEFAULT_CONTEXT = "com.sun.jndi.ldap.LdapCtxFactory";
	private final static String DEFAULT_SECURITY = "none";
	private final static String DEFAULT_BASEDN = "o=NSN";
	
	private String ldapServer;
	private String contextFactory;
	private String securityAuth;
	private String ldapBaseDN;
	
	public LDAPManager ()
	{
		this(DEFAULT_LDAPSERVER, DEFAULT_BASEDN);
	}
	
	public LDAPManager (String server, String baseDN)
	{
		this.ldapServer = server;
		this.ldapBaseDN = baseDN;
		this.contextFactory = DEFAULT_CONTEXT;
		this.securityAuth = DEFAULT_SECURITY;
	}
	
	/**
	 * Obtains user information by the specified user name
	 * 
	 * @param userAccount
	 */
	@SuppressWarnings("rawtypes")
	public ModelMember getUserInfo (String userAccount) throws NamingException
	{
		ModelMember userInfo = new ModelMember();
		
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		props.put(Context.PROVIDER_URL, ldapServer);
		
		try 
		{
			// Create initial context
			DirContext ctx = new InitialDirContext(props);
			System.out.println("Bind Successful");
			Attributes attributes = ctx.getAttributes("");
			Attribute attribute = attributes.get("defaultNamingContext");
			
			// this entry is available on Active Directory.
			if(attribute != null && attribute.get() != null)
			{
				System.out.println(attribute.get().toString());
			}
			
			attribute = attributes.get("namingcontexts");
			
			if(attribute == null) 
			{
				System.out.println("namingcontexts attribute not found in root DSE of " + ldapServer);
			}
			
			System.out.println(attribute.get().toString());

			//Searching..
			NamingEnumeration results = null;
			
			SearchControls controls = new SearchControls();
			controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			results = ctx.search(ldapBaseDN, "(uid=" + userAccount + ")", controls);

			System.out.println("Searching...");
			
			while (results.hasMore()) 
			{
				System.out.println("Found Something..");
				SearchResult searchResult = (SearchResult) results.next();
				attributes = searchResult.getAttributes();
				
				userInfo.setUserName(attributes.get("cn").get().toString());
				userInfo.setUserId(attributes.get("uidNumber").get().toString());
				userInfo.setUserMail(attributes.get("mail").get().toString());
				userInfo.setUserAccount(attributes.get("uid").get().toString());
				
//				Attribute attr1 = attributes.get("cn");
//				Attribute attr2 = attributes.get("uidNumber");
//				Attribute attr3 = attributes.get("gidNumber");
//				Attribute attr4 = attributes.get("homeDirectory");
//				Attribute attr5 = attributes.get("mail");
//				System.out.println("Searched:"+(String) attr2.get());
//				System.out.println("Searched:"+(String) attr3.get());
//				System.out.println("Searched:"+(String) attr4.get());
//				System.out.println("Searched:"+(String) attr5.get());
//				System.out.println("Searched:"+(String) attr1.get());
			}
			
			ctx.close();
			
			return userInfo;
		}
		catch (NamingException e) 
		{
			throw new NamingException(e.getMessage());
		}
	}
	
	/**
	 * Authenticates the provided user name and password from LDAP server
	 * 
	 * @param userName
	 * @param userPassword
	 * @return
	 */
	public boolean authenticate (String userAccount, String userPassword) throws AuthenticationException
	{
		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, contextFactory);
		env.put(Context.PROVIDER_URL, ldapServer);
		env.put(Context.SECURITY_AUTHENTICATION, securityAuth);
		env.put(Context.SECURITY_CREDENTIALS, userPassword);  

		SearchControls searchCtrls = new SearchControls();
		searchCtrls.setReturningAttributes(new String[] {});
		searchCtrls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		
		String criteria = "(&(uid=" + userAccount + "))";
		DirContext ctx = null;
		try 
		{
			ctx = new InitialDirContext(env);
			NamingEnumeration<SearchResult> answer = ctx.search(ldapBaseDN, criteria, searchCtrls);

			String fullDN = null;
			if (answer.hasMore()) 
			{
				// authentication successfully
				fullDN = answer.next().getNameInNamespace();
				ctx.close();
				ctx = null;
				
				env.put(Context.SECURITY_AUTHENTICATION, "simple");
				env.put(Context.SECURITY_PRINCIPAL, fullDN);
				env.put(Context.SECURITY_CREDENTIALS, userPassword);
				
				ctx = new InitialDirContext(env);
				
				return true;
			}
			else
			{
				throw new AuthenticationException("Authentication failed. Wrong username or password.");
			}
			
		} 
		catch (NamingException e) 
		{
			throw new AuthenticationException("Authentication failed. Wrong username or password.", e);
		}
	}

	public String getLdapServer()
	{
		return ldapServer;
	}

	public void setLdapServer(String ldapServer)
	{
		this.ldapServer = ldapServer;
	}

	public String getContextFactory()
	{
		return contextFactory;
	}

	public void setContextFactory(String contextFactory)
	{
		this.contextFactory = contextFactory;
	}

	public String getSecurityAuth()
	{
		return securityAuth;
	}

	public void setSecurityAuth(String securityAuth)
	{
		this.securityAuth = securityAuth;
	}

	public String getLdapBaseDN()
	{
		return ldapBaseDN;
	}

	public void setLdapBaseDN(String ldapBaseDN)
	{
		this.ldapBaseDN = ldapBaseDN;
	}
	
}
