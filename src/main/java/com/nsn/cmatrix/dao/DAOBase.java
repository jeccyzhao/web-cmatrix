package com.nsn.cmatrix.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import com.nsn.cmatrix.exception.DAOException;
import com.nsn.cmatrix.model.ModelBase;
import com.nsn.cmatrix.web.PaginationSupport;

/**
 * The base repository
 * 
 * @author Jeccy.Zhao
 *
 * @param <T> Generic model object
 */
public interface DAOBase<T extends ModelBase>  
{
	/**
	 * Save or Update the object in database
	 * 
	 * @param domain Object to be saved of updated
	 * @return
	 */
	public void saveOrUpdate (final T model) throws DAOException;
	
	/**
	 * Remove the object from database
	 * 
	 * @param domain Object to be deleted
	 */
	public void remove(final T domain) throws DAOException;
	
	/**
	 * Get object by identity id.
	 * 
	 * @param id Identity of object
	 * @return Object
	 */
	public T get(Serializable id) throws DAOException;
	
	/**
	 * Get all of available objects
	 * 
	 * @return all available objects
	 * @throws DAOException
	 */
	public List<T> getAll() throws DAOException;
	
	/**
	 * Get object list by query criteria
	 * 
	 * @param criteria 
	 *          the query criteria,including condition and the orders
	 * @return list of objects
	 */
	public List<T> getListByCriteria(final DetachedCriteria criteria) throws DAOException;
	
	/**
	 * Get object list by query criteria
	 * 
	 * @param criteria 
	 *          the query criteria,including condition and the orders
	 * @param firstResult
	 *          the first index
	 * @param maxResult
	 *          the end index
	 * @return list of objects
	 */
	public List<T> getListByCriteria(final DetachedCriteria criteria, 
			int firstResult,int maxResult) throws DAOException;
	
	/**
	 * Find object by pagination support
	 * 
	 * @param criteria 
	 *           the query criteria,including condition and the orders
	 * @param pageSize 
	 *           the size of page to show
	 * @param startIndex 
	 *           the start index to search
	 * @return list of objects wrapped by pager
	 */
	public PaginationSupport<T> findPageByCriteria(final DetachedCriteria criteria,
			int pageSize,int startIndex) throws DAOException;
	

	/**
	 * Query object pagination by the specified query parameters and object
	 * 
	 * @param objectClass
	 * @param params
	 * @param pageSize
	 * @param startIndex
	 * @return
	 * @throws DAOException
	 */
	public PaginationSupport<T> queryFieldsListForPaging(final Class<?> objectClass, 
			Map<String, List<Object>> params, int pageSize, int startIndex) throws DAOException;
	
	/**
	 * Query object pagination by the specified query parameters and object
	 * 
	 * @param objectClass
	 * @param params
	 * @param returnFields
	 * @param pageSize
	 * @param startIndex
	 * @return
	 */
	public PaginationSupport<T> queryFieldsListForPaging(final String objectClassName, 
			Map<String, List<Object>> params, int pageSize, int startIndex) throws DAOException, ClassNotFoundException;
	
	/**
	 * Obtains objects with the specified native SQL
	 * 
	 * @param nativeSql
	 *          the native SQL string
	 * @return list of objects
	 */
	public List<Object> findListByNativeSQL(final String nativeSql) throws DAOException;
	
	/**
	 * Obtains objects with the specified native SQL, 
	 * and converted as the given CLAZ afterwards. 
	 * 
	 * @param nativeSql
	 *          the native SQL string
	 * @param claz
	 *          the target class
	 * @return list of objects
	 */
	@SuppressWarnings("rawtypes")
	public List findListByNativeSQL(final String nativeSql, final Class<?> claz) throws DAOException;
	
	/**
	 * Get object list by HSQL query 
	 * 
	 * @param hsql
	 *         the HSQL string
	 * @return list of objects
	 */
	public List<T> findListByHSQL(final String hsql) throws DAOException;
	
	/**
	 * Executes the specified native SQL and returns the affected row count. 
	 * It's already deprecated since the affected identity cannot be returned.
	 * 
	 * @param nativeSql
	 *          the native SQL string
	 * @return the affected row count
	 */
	public int execUpdateByNativeSQL(final String nativeSql) throws DAOException;
	
	/**
	 * Executes the specified HQL and returns the affected row count. 
	 * It's already deprecated since the affected identity cannot be returned.
	 * 
	 * @param hql
	 * @param params
	 * @return
	 * @throws DAOException
	 */
	public int execUpdateByHQL(final String hql, final Object[] params) throws DAOException;
	
	/**
	 * Obtains an unique object from database
	 * 
	 * @param hsql
	 *                the HSQL
	 * @param params
	 *                the parameter object list
	 * @return unique object entity
	 * @throws DAOException
	 */
	 public Object findUnique (final String hsql, final Object[] params) throws DAOException;
}
