package com.scm.dao.custom.impl;

import org.springframework.stereotype.Repository;

import com.scm.dao.custom.GudangGrupDaoCustom;
import com.scm.entities.GudangGrup;


/**
 * Repository Custom class
 * ssssss
 * @author Adhityarismawan
 */

@Repository("GudangGrupDaoCustom")
public class GudangGrupDaoCustomImpl extends AbstractDAO<GudangGrup>  implements GudangGrupDaoCustom {

	@Override
	protected Class<GudangGrup> getDomainClass() {
		return GudangGrup.class;
	}

	

}
