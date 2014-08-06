package com.nsn.cmatrix.exception;

/**
 * Authentication Exception
 * 
 * @author Jeccy.Zhao
 */
public class AuthenticationException extends Exception
{
	private static final long serialVersionUID = -9074348094784704205L;
	
	public AuthenticationException(String message) 
	{
		super(message);
	}

	public AuthenticationException(Throwable cause) 
	{
		super(cause);
	}

	public AuthenticationException(String message, Throwable cause) 
	{
		super(message, cause);
	}
	
}