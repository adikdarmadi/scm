package com.scm.dao.custom;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.scm.entitiesAuditLog.AuditLog;


public interface DAO<T> {

	List<T> findAll();

	T findById(String id);
	
	T save(T obj);
	
	T insertLog(T obj);

	T update(T obj);

	T delete(T obj);
	
	Object execUnique(String query);

	Object execUnique(String query, Object[] parameters);

	Object execList(String query);

	Object execList(String query, Object[] parameters);

	Object execUnique(Query query, Session session);

	Object execList(Query query, Session session);

	void deleteById(String id);
	
	T merge(T obj);

	T saveUpdate(T obj);
	
	


}
