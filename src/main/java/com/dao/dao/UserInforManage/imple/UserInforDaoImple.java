package com.dao.dao.UserInforManage.imple;


import com.dao.UserInforManage.UserInforDao;
import com.model.Account;
import org.springframework.stereotype.Repository;


import com.dao.common.imple.BaseDaoHibernate4;
import com.model.UserInfor;
@Repository
public class UserInforDaoImple extends BaseDaoHibernate4<UserInfor> implements UserInforDao{

	public UserInforDaoImple() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public void saveAccount(Account account) {
		getSessionFactory().getCurrentSession().update(account);
	}
}
