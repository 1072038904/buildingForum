package com.dao.dao.AccountManage;
import com.dao.common.BaseDao;
import com.model.Account;
public interface LoginDao extends BaseDao<Account>{
	Account getAccountByName(String Name);
}
