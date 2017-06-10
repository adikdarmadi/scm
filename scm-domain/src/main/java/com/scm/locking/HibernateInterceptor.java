package com.scm.locking;

import java.io.Serializable;
import java.util.Iterator;

import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Adik
 */

@Service
public class HibernateInterceptor extends EmptyInterceptor {


	@Autowired
	private LoggingJdbcDao loggingJdbcDao;
	

	@Override
	public boolean onFlushDirty(Object entity, Serializable id2, Object[] currentState, Object[] previousState,String[] propertyNames, Type[] types) {
		Boolean re=loggingJdbcDao.checkData(entity);
		return re;
	}

	private OptimisticLockChecker getChecker() {
		return ApplicationContextProvider.getApplicationContext().getBean(OptimisticLockChecker.class);
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		System.out.println("masuk 2");
		return true;

	}

	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		System.out.println("masuk 3");
	}

	public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		System.out.println("masuk 4");
		return true;
	}

	@Override
	public String onPrepareStatement(String sql) {
		System.out.println("masuk 5");
		return sql;
	}

	@Override
	public void postFlush(Iterator entities) throws CallbackException {
		System.out.println("masuk 6");
	}

	@Override
	public void preFlush(Iterator entities) {
		System.out.println("masuk 7");
	}
}
