package com.dao.AccountManage.imple;
import org.springframework.stereotype.Repository;

import com.dao.AccountManage.LoginDao;
import com.dao.common.imple.BaseDaoHibernate4;
import com.model.Account;
@Repository
public class LoginDaoImple extends BaseDaoHibernate4<Account>implements LoginDao{

	public LoginDaoImple() {

	}

}
