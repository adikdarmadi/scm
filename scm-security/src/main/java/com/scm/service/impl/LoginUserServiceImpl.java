package com.scm.service.impl;

import java.io.IOException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.scm.dao.custom.UserDaoCustom;
import com.scm.entities.User;
import com.scm.service.LoginUserService;
import com.scm.util.CommonUtil;
import com.scm.util.PasswordUtil;
import com.scm.vo.AuthVO;
import com.scm.vo.UserVO;

/**
 * Implement class for LoginUserService
 * 
 * @author Adik
 */
@Service("LoginUserService")
public class LoginUserServiceImpl implements LoginUserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginUserServiceImpl.class);

	@Autowired
	private UserDaoCustom userDaoCustom;

	private ModelMapper modelMapper = new ModelMapper();
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public UserVO signIn(AuthVO authVO)  {

		 User user = userDaoCustom.findById(authVO.getId());
		if (CommonUtil.isNotNullOrEmpty(user)) {

			PasswordUtil passwordUtil = new PasswordUtil();
			Boolean isValidPassword = false;
			try {
				isValidPassword = passwordUtil.isPasswordEqual(authVO.getPassword(), user.getPassword());
			} catch (IOException ioe) {
				LOGGER.error("Password not match : " + ioe.getMessage());
				return null;
			}

			if (!isValidPassword) {
				LOGGER.error("Password do not match");
				return null;
			}
			// to do validasi yang advanced di sini

			modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			UserVO vo = modelMapper.map(user, UserVO.class);
			/*vo.setId(authVO.getId());
			vo.setPassword(authVO.getPassword());*/
			return vo;

		} else {
			LOGGER.error("User not found");
			return null;
		}
	}


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
