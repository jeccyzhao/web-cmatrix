package com.nsn.cmatrix.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

import com.nsn.cmatrix.dao.DAOBase;
import com.nsn.cmatrix.exception.DAOException;
import com.nsn.cmatrix.model.ModelBase;
import com.nsn.cmatrix.web.PaginationSupport;

public class DAOBaseImpl<T extends ModelBase>
implements DAOBase<T>
{
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * The entity class
	 */
	protected Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public DAOBaseImpl()
	{
		entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.nsn.cmatrix.dao.DAOBase#saveOrUpdate(com.nsn.cmatrix.model.ModelBase)
	 */
	@Override
	public void saveOrUpdate(T model) 
	{
		getSession().saveOrUpdate(model);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.nsn.cmatrix.dao.DAOBase#remove(com.nsn.cmatrix.model.ModelBase)
	 */
	@Override
	public void remove(T domain) throws DAOException
	{
		if (domain != null)
		{
			getSession().delete(domain);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.nsn.cmatrix.dao.DAOBase#get(java.io.Serializable)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public T get(Serializable id) throws DAOException
	{
		return (T) getSession().get(entityClass, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.nsn.cmatrix.dao.DAOBase#getAll()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> getAll() throws DAOException
	{
		Criteria execCriteria = DetachedCriteria.forClass(entityClass).getExecutableCriteria(getSession());
		
		int rowCount = ((Long) execCriteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		execCriteria.setProjection(null);
		execCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		execCriteria.setFirstResult(0);
		execCriteria.setMaxResults(rowCount);
		
		return execCriteria.list();
	}

	@Override
	public List<T> getListByCriteria(DetachedCriteria criteria)
			throws DAOException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> getListByCriteria(DetachedCriteria criteria,
			int firstResult, int maxResult) throws DAOException
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.nsn.cmatrix.dao.DAOBase#findPageByCriteria(org.hibernate.criterion.DetachedCriteria, int, int)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public PaginationSupport<T> findPageByCriteria(DetachedCriteria criteria,
			int pageSize, int startIndex) throws DAOException
	{
		Criteria execCriteria = criteria.getExecutableCriteria(getSession());
		
		int rowCount = ((Long)execCriteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		execCriteria.setProjection(null);
		execCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		execCriteria.setFirstResult(startIndex);
		
		if(pageSize > 0)
		{
			execCriteria.setMaxResults(pageSize);	
		}
		else
		{
			execCriteria.setMaxResults(rowCount);
		}
		List<T> items = execCriteria.list();
		return rowCount > 0 ? new PaginationSupport<T>(items, rowCount, startIndex, 
				pageSize >0 ? pageSize : rowCount) : null;
				
	}

	@Override
	public PaginationSupport<T> queryFieldsListForPaging(Class<?> objectClass,
			Map<String, List<Object>> params, int pageSize, int startIndex)
			throws DAOException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginationSupport<T> queryFieldsListForPaging(
			String objectClassName, Map<String, List<Object>> params,
			int pageSize, int startIndex) throws DAOException,
			ClassNotFoundException
	{
		// TODO Auto-generated method stub
		return null;
	}
	/*
	 * (non-Javadoc)
	 * @see com.nsn.cmatrix.dao.DAOBase#findListByNativeSQL(java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Object> findListByNativeSQL(String nativeSql)
			throws DAOException
	{
		SQLQuery query = getSession().createSQLQuery(nativeSql);
		return query.list();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.nsn.cmatrix.dao.DAOBase#findListByNativeSQL(java.lang.String, java.lang.Class)
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public List findListByNativeSQL(String nativeSql, Class<?> claz)
			throws DAOException
	{
		SQLQuery query = getSession().createSQLQuery(nativeSql);
		return query.addEntity(claz).list();
	}

	@Override
	public List<T> findListByHSQL(String hsql) throws DAOException
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.nsn.cmatrix.dao.DAOBase#execUpdateByNativeSQL(java.lang.String)
	 */
	@Override
	public int execUpdateByNativeSQL(String nativeSql) throws DAOException
	{
		return getSession().createSQLQuery(nativeSql).executeUpdate();
	}

	/*
	 * (non-Javadoc)
	 * @see com.nsn.cmatrix.dao.DAOBase#execUpdateByHQL(java.lang.String, java.lang.Object[])
	 */
	@Override
	public int execUpdateByHQL(String hql, Object[] params) throws DAOException
	{
		Query query = getSession().createQuery(hql);
		
		if (params != null)
		{
			for (int i = 0, len = params.length; i < len; i++) 
			{
				query.setString(i, params[i].toString());
			}
		}
		
		return query.executeUpdate();
	}

	@Override
	public Object findUnique(String hsql, Object[] params) throws DAOException
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Obtains the current session. 
	 *  
	 * @return
	 */
	protected final Session getSession() 
	{
		return sessionFactory.getCurrentSession();
	}
	
	public Class<T> getEntityClass()
	{
		return entityClass;
	}

}
