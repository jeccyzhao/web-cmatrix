package com.nsn.cmatrix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Common Controller
 * 
 * @author Jeccy.Zhao
 *
 */
@Controller
@RequestMapping("/common")
public class CommonController 
{
	/**
	 * Resource not found page
	 */
	@RequestMapping("/resource_not_found")
	public String resourceNotFound() 
	{
		return "/common/resource_not_found";
	}
	
	/**
	 * Error page
	 */
	@RequestMapping("/error")
	public String error() 
	{
		return "/common/error";
	}
}
