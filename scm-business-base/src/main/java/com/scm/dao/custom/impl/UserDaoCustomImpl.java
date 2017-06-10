package com.scm.dao.custom.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.scm.dao.custom.UserDaoCustom;
import com.scm.entities.User;


/**
 * Repository Custom class
 * ssssss
 * @author Adhityarismawan
 */

@Repository("UserDaoCustom")
public class UserDaoCustomImpl extends AbstractDAO<User>  implements UserDaoCustom {

	@Override
	protected Class<User> getDomainClass() {
		// TODO Auto-generated method stub
		return User.class;
	}



}
