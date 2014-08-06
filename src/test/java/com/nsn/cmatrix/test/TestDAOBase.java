package com.nsn.cmatrix.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nsn.cmatrix.exception.DAOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/**/applicationContext.xml")
public abstract class TestDAOBase extends AbstractTransactionalJUnit4SpringContextTests
{
	/**
	 * Test for saveOrUpdate
	 * 
	 * @throws DAOException
	 */
	public abstract void testSaveOrUpdate () throws DAOException;
	
	/**
	 * Test for getAll 
	 * 
	 * @throws DAOException
	 */
	public abstract void testGetAll() throws DAOException;
	
	/**
	 * Test for getPaging
	 * 
	 * @throws DAOException
	 */
	public abstract void testGetPaging () throws DAOException;
	
}
