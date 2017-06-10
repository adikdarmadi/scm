package com.scm.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.enumeration.ActionEnum;
import com.scm.dao.custom.GudangGrupDaoCustom;
import com.scm.entities.GudangGrup;
import com.scm.service.AuditLogService;
import com.scm.service.GudangGrupService;
import com.scm.service.UserService;
import com.scm.util.DateUtil;
import com.scm.vo.GudangGrupVO;

@Service("gudangGrupService")
public class GudangGrupServiceImpl implements GudangGrupService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GudangGrupServiceImpl.class);

	@Autowired
	private GudangGrupDaoCustom gudangGrupDaoCustom;
	
	@Autowired
	private AuditLogService auditLogService;
	
	@Autowired
	private UserService userService;
	
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	@Transactional(value="log")
	public Map<String,Object> editGudangGrup(GudangGrupVO vo,Integer version) {
		LOGGER.info(userService.getUser().getNama() +" execute editGudangGrup");
		Map<String,Object> result=new HashMap<String,Object>(); 
			GudangGrup model=gudangGrupDaoCustom.findById(vo.getId());
			model.setCreateBy(userService.getUser().getNama());
			model.setVersion(version);
			model.setCreateDate(DateUtil.now());
			model.setIsActive(true);
			GudangGrup gudangGrup=gudangGrupDaoCustom.save(model);
			auditLogService.insertAuditLog(ActionEnum.Updated.getVal(), model,"Gudang Grup");	
			result.put("id", gudangGrup.getId());
			result.put("isActive", gudangGrup.getIsActive());
		
		return result;
	}

	@Override
	public Map<String, Object> findAllGudangGrup(Map<String, String> pathVariables) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(value="log")
	public Map<String, Object> saveGudangGrup(GudangGrupVO p) {
		LOGGER.info(userService.getUser().getNama() +" execute saveGudangGrup ");
		GudangGrup model=modelMapper.map(p, GudangGrup.class);
		model.setId(model.getId().toUpperCase());
		model.setCreateBy(userService.getUser().getId());
		model.setCreateDate(DateUtil.now());
		model.setIsActive(true);
		GudangGrup gudangGrup=gudangGrupDaoCustom.save(model);
		auditLogService.insertAuditLog(ActionEnum.Saved.getVal(), model,"Gudang Grup");
		Map<String,Object> result=new HashMap<String,Object>(); 
		result.put("id", gudangGrup.getId());
		result.put("isActive", gudangGrup.getIsActive());
		return result;
	}

	@Override
	public Map<String, Object> findById(String id) {
		LOGGER.info(userService.getUser().getNama() +" execute findById "+id);
		Map<String,Object> result=new HashMap<String,Object>(); 
		GudangGrup gudangGrup=gudangGrupDaoCustom.findById(id);
		result.put("gudangGrup", gudangGrup);
		return result;
	}

	@Override
	public Map<String, Object> deleteGudangGrup(String id) {
		Map<String,Object> result=new HashMap<String,Object>(); 
		gudangGrupDaoCustom.deleteById(id);
		result.put("id", id);
		return result;
	}

	@Override
	public Map<String, Object> isActiveGudangGrup(String id,Integer version) {
		LOGGER.info(userService.getUser().getNama() +" execute isActiveGudangGrup "+id);
		
		GudangGrup model = gudangGrupDaoCustom.findById(id);
		
		if(model.getIsActive()){
			model.setIsActive(false);
			model.setDateNonActive(DateUtil.now());
		}else{
			model.setIsActive(true);
			model.setDateNonActive(null);
		}
		
		model.setLastUpdateBy(userService.getUser().getId());
		model.setLastUpdateDate(DateUtil.now());
		model.setVersion(version);
		
		GudangGrup gudangGrup=gudangGrupDaoCustom.save(model);
		if(model.getIsActive()){
			auditLogService.insertAuditLog(ActionEnum.Actived.getVal(), model,"Gudang Grup");
		}else{
			auditLogService.insertAuditLog(ActionEnum.NonActived.getVal(), model,"Gudang Grup");
		}
		Map<String,Object> result=new HashMap<String,Object>(); 
		result.put("id", gudangGrup.getId());
		result.put("isActive", gudangGrup.getIsActive());
		return result;
	}
	
	
	


}
