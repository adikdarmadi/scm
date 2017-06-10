package com.scm.security;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.scm.dao.custom.UserDaoCustom;
import com.scm.entities.User;
import com.scm.util.CommonUtil;

/**
 * UserService implements org.springframework.security.core.userdetails.UserDetailsService
 * 
 * @author Adik
 */
public class UserService implements
		org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	private UserDaoCustom userDao;

	private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

	public UserService() {
		super();
	}

	/**
	 * this method is called for rest authentication from client
	 * 
	 * @author Adik
	 */
	@Override
	public final org.springframework.security.core.userdetails.User loadUserByUsername(String id) throws UsernameNotFoundException {
		User user = userDao.findById(id);
		if (CommonUtil.isNullOrEmpty(user)) {
			throw new UsernameNotFoundException("user not found");
		}
		//validasi tambahan lakukan di sini
		
//		GrantedAuthority authority = new SimpleGrantedAuthority(loginUser
//				.getKelompokUser().getKelompokUser());
		GrantedAuthority authority = new SimpleGrantedAuthority("USER");
		UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(user.getId(), user.getPassword(),Arrays.asList(authority));
		detailsChecker.check(userDetails);
		return (org.springframework.security.core.userdetails.User) userDetails;
	}
}