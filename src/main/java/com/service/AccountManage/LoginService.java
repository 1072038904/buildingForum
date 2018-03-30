package com.service.AccountManage;

import com.model.Account;

public interface LoginService {
	public  Account isValid(Account account);
	public void saveAccount(Account account);
	public int isExisted(Account account);
	public Account findAccount(Account account);
}
