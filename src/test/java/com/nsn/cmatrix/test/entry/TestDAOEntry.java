package com.nsn.cmatrix.test.entry;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.junit.Test;

import com.nsn.cmatrix.dao.DAOEntry;
import com.nsn.cmatrix.exception.DAOException;
import com.nsn.cmatrix.model.ModelEntry;
import com.nsn.cmatrix.test.TestDAOBase;
import com.nsn.cmatrix.util.UtilCollection;
import com.nsn.cmatrix.web.PaginationSupport;

public class TestDAOEntry extends TestDAOBase
{
	@Resource
	private DAOEntry daoEntry;
	
	/*
	 * (non-Javadoc)
	 * @see com.nsn.cmatrix.test.TestBase#testSaveOrUpdate()
	 */
	@Test
	@Override
	public void testSaveOrUpdate() throws DAOException
	{
		ModelEntry entry = new ModelEntry();
		
		entry.setSourceSystem("WAS");
		entry.setSourcePort("any");
		entry.setDestPort("8080");
		entry.setDestSystem("AAA OAM");
		entry.setOwner("x36zhao");
		entry.setProject("EP2");
		entry.setCreationTime(new Date());
		
		daoEntry.saveOrUpdate(entry);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.nsn.cmatrix.test.TestBase#testGetAll()
	 */
	@Test
	@Override
	public void testGetAll() throws DAOException
	{
		List<ModelEntry> entries = daoEntry.getAll();
		if (UtilCollection.isNotEmpty(entries))
		{
			for (ModelEntry entry : entries)
			{
				System.out.println(entry.getId());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.nsn.cmatrix.test.TestBase#testGetPaging()
	 */
	@Test
	@Override
	public void testGetPaging() throws DAOException
	{
		DetachedCriteria criteria = DetachedCriteria.forClass(ModelEntry.class);
		PaginationSupport<ModelEntry> pagingData = daoEntry.findPageByCriteria(criteria, 3, 0);
		if (pagingData != null && UtilCollection.isNotEmpty(pagingData.getItems()))
		{
			for (ModelEntry entry : pagingData.getItems())
			{
				System.out.println(entry.getId());
			}
		}
	}
	
}
