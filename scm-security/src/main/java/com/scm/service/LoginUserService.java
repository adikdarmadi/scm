package com.scm.service;

import com.scm.entities.User;
import com.scm.vo.AuthVO;
import com.scm.vo.UserVO;


/**
 * LoginUser Service
 * 
 * @author Adik
 */
public interface LoginUserService{
	
	UserVO signIn(AuthVO authVO);
	
	User getUser();

}
