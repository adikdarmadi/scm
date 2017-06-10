package com.scm.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.scm.dao.custom.UserDaoCustom;
import com.scm.entities.User;
import com.scm.service.UserService;

/**
 * Implement class for LoginUserService
 * 
 * @author Adik
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDaoCustom userDaoCustom;
	
	@Override
	public User getUser() {
		User user = null;
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String id = principal.toString();
            user = userDaoCustom.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
	
	
}
