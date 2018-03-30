package com.dao.dao.AccountManage.imple;
import org.springframework.stereotype.Repository;

import com.dao.AccountManage.LoginDao;
import com.dao.common.imple.BaseDaoHibernate4;
import com.model.Account;
@Repository
public class LoginDaoImple extends BaseDaoHibernate4<Account>implements LoginDao{

	public LoginDaoImple() {

	}

	@Override
	public Account getAccountByName(String Name) {
		Account account=(Account)getSessionFactory().getCurrentSession()
				.createQuery("from Account account where account.username=:username")
				.setParameter("username",Name).uniqueResult();
		System.out.println(account);
		return account;
	}
}
