package com.scm.service;

import com.scm.entities.GudangGrup;

/**
 * User Service
 * 
 * @author Adhityarismawan
 */
public interface AuditLogService{
	
	<T> void insertAuditLog(String string, T model, String string2);


}
