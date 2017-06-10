package com.scm.service.impl;


import java.lang.reflect.Field;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.dao.custom.AuditLogDaoCustom;
import com.scm.entitiesAuditLog.AuditLog;
import com.scm.service.AuditLogService;
import com.scm.service.UserService;
import com.scm.util.DateUtil;



@Service("auditLogService")
public class AuditLogServiceImpl implements AuditLogService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuditLogServiceImpl.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuditLogDaoCustom auditLogDaoCustom;
	
	private ModelMapper modelMapper = new ModelMapper();

	
	@Override
	public <T> void insertAuditLog(String action, T obj,String module) {
		// TODO Auto-generated method stub
		
		String id = "";
		try {
			Field field = obj.getClass().getDeclaredField("id");
			field.setAccessible(true);
			Object ids = field.get(obj);
			id = ids.toString();
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		AuditLog auditLog = new AuditLog();
		auditLog.setAction(action);
		auditLog.setCreatedDate(DateUtil.now());
		auditLog.setEntityId(id);
		auditLog.setEntityName(obj.getClass().toString());
		auditLog.setUsername(userService.getUser().getNama());
		auditLog.setDetail(module + " ID : " + id);
		auditLog.setModule(obj.getClass().getSimpleName());
		auditLogDaoCustom.insertLog(auditLog);
		
	}

	

}
