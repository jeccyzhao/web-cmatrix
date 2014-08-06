package com.nsn.cmatrix.test.project;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.nsn.cmatrix.dao.DAOProject;
import com.nsn.cmatrix.dao.DAOProjectSheet;
import com.nsn.cmatrix.exception.DAOException;
import com.nsn.cmatrix.model.ModelProjectSheet;
import com.nsn.cmatrix.test.TestDAOBase;

public class TestDAOProjectSheet extends TestDAOBase
{
	@Resource
	private DAOProjectSheet daoProjectSheet;
	
	@Resource
	private DAOProject daoProject;
	
	/*
	 * (non-Javadoc)
	 * @see com.nsn.cmatrix.test.TestBase#testSaveOrUpdate()
	 */
	@Test
	@Override
	public void testSaveOrUpdate() throws DAOException
	{
		ModelProjectSheet sheet = new ModelProjectSheet();
		
		sheet.setProject(daoProject.get("1"));
		sheet.setSheetName("SBI");
		sheet.setCreationTime(new Date());
		
		daoProjectSheet.saveOrUpdate(sheet);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.nsn.cmatrix.test.TestBase#testGetAll()
	 */
	@Test
	@Override
	public void testGetAll() throws DAOException
	{
	}

	/*
	 * (non-Javadoc)
	 * @see com.nsn.cmatrix.test.TestBase#testGetPaging()
	 */
	@Test
	@Override
	public void testGetPaging() throws DAOException
	{
	}
	
}
