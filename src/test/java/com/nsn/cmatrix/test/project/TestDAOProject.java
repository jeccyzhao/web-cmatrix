package com.nsn.cmatrix.test.project;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.nsn.cmatrix.dao.DAOProject;
import com.nsn.cmatrix.exception.DAOException;
import com.nsn.cmatrix.model.ModelProject;
import com.nsn.cmatrix.test.TestDAOBase;

public class TestDAOProject extends TestDAOBase
{
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
		ModelProject project = new ModelProject();
		
		project.setProjectName("NetAct 15");
		project.setProjectStatus(1);
		project.setProjectDesc("NetAct8 EP2");
		project.setCreationTime(new Date());
		
//		ModelProjectSheet sheet = new ModelProjectSheet();
//		sheet.setCreationTime(new Date());
//		sheet.setSheetName("SBI");
//		sheet.setProject(project);
//		project.getProjectSheets().add(sheet);
		
		daoProject.saveOrUpdate(project);
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
