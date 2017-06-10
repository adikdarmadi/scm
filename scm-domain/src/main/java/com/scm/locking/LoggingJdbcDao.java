package com.scm.locking;

import java.util.List;

import javax.sql.DataSource;


/**
 * simple dao LoggingJdbcDao
 * 
 * @author Roberto
 */
public interface LoggingJdbcDao {
	/**
	 * This is the method to be used to initialize database resources ie.
	 * connection.
	 */
	public void setDataSource(DataSource ds);

	public Boolean checkData(Object entity);

	
}