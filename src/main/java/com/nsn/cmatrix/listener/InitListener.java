package com.nsn.cmatrix.listener;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

@Component("initListener")
public class InitListener implements ServletContextAware, ApplicationListener<ContextRefreshedEvent> 
{
	/** servletContext */
	private ServletContext servletContext;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) 
	{
		if (servletContext != null && event.getApplicationContext().getParent() == null) 
		{
			
		}
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) 
	{
		this.servletContext = servletContext;
	}
}
