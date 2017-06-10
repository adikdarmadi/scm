package com.scm.dao.custom.impl;

import org.springframework.stereotype.Repository;

import com.scm.dao.custom.AuditLogDaoCustom;
import com.scm.entitiesAuditLog.AuditLog;

/**
 * Repository Custom class
 * 
 * @author Adhityarismawan
 */

@Repository("AuditLogDaoCustom")
public class AuditLogDaoCustomImpl extends AbstractDAO<AuditLog> implements AuditLogDaoCustom {

	@Override
	protected Class<AuditLog> getDomainClass() {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}
