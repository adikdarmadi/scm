package com.scm.dao.custom.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.scm.dao.custom.DAO;

@Repository
@SuppressWarnings("unchecked")
public abstract class AbstractDAO<T> implements DAO<T> {

	protected abstract Class<T> getDomainClass();

	@Autowired
	@Qualifier(value = "bisnis")
	public SessionFactory sessionFactory;

	@Autowired
	@Qualifier(value = "log")
	public SessionFactory sessionFactoryLog;

	public Session getSession() {
		Session session = null;
		session = sessionFactory.getCurrentSession();
		return session;
	}

	public Session getSessionLog() {
		Session session = null;
		session = sessionFactoryLog.getCurrentSession();
		return session;
	}

	@Override
	public List<T> findAll() {
		Session session = getSession();
		return (List<T>) session
				.createQuery("from " + getDomainClass().getName() + " x where x.isActive=true order by x.id asc")
				.list();
	}

	@Override
	public T findById(String id) {
		Session session = getSession();
		Query query = session
				.createQuery("from " + getDomainClass().getSimpleName() + " domain where domain.id =:id    ");
		query.setString("id", id);
		return (T) query.uniqueResult();

	}

	@Override
	public T save(T obj) {
		sessionFactory.getCurrentSession().saveOrUpdate(obj);
		return obj;
		
    }

	@Override
	public T insertLog(T obj) {
		obj=(T) sessionFactoryLog.getCurrentSession().save(obj);
		return obj;
	}

	@Override
	public T update(T obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T delete(T obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object execUnique(String query) {
		Session session = getSession();
		try {
			return session.createQuery(query).uniqueResult();
		} catch (HibernateException e) {
			throw SessionFactoryUtils.convertHibernateAccessException(e);
		}
	}

	@Override
	public Object execUnique(String query, Object[] parameters) {
		Session session = getSession();
		try {
			Query object = session.createQuery(query);
			setParameters(object, parameters);
			return object.uniqueResult();
		} catch (HibernateException e) {
			throw SessionFactoryUtils.convertHibernateAccessException(e);
		}
	}

	@Override
	public Object execList(String query) {
		Session session = getSession();
		try {
			return session.createQuery(query).list();
		} catch (HibernateException e) {
			throw SessionFactoryUtils.convertHibernateAccessException(e);
		}
	}

	@Override
	public Object execList(String query, Object[] parameters) {
		Session session = getSession();
		try {
			Query object = session.createQuery(query);
			setParameters(object, parameters);
			return object.list();
		} catch (HibernateException e) {
			throw SessionFactoryUtils.convertHibernateAccessException(e);
		}
	}

	@Override
	public Object execUnique(Query query, Session session) {
		try {
			return query.uniqueResult();
		} catch (HibernateException e) {
			throw SessionFactoryUtils.convertHibernateAccessException(e);
		}
	}

	@Override
	public Object execList(Query query, Session session) {
		try {
			return query.list();
		} catch (HibernateException e) {
			throw SessionFactoryUtils.convertHibernateAccessException(e);
		}
	}

	@Override
	public void deleteById(String id) {
		Session session = getSession();
		Query query = session.createQuery("DELETE FROM " + getDomainClass().getSimpleName() + " x where x.id =:id");
		query.setString("id", id);
		query.executeUpdate();

	}

	@Override
	public T saveUpdate(T obj) {
		Session session = getSession();
		session.saveOrUpdate(obj);
		return obj;
	}
	
	
	@Override
	public T merge(T obj) {
		Session session = getSession();
		session.merge(obj);
		return obj;
	}
	
	private void setParameters(Query query, Object parameters[]) {
		if (parameters == null || parameters.length == 0) {
			return;
		}

		for (int i = 0; i < parameters.length; i++) {
			if (parameters[i] == null) {

			}
			query.setParameter(i, parameters[i]);
		}
	}
}
