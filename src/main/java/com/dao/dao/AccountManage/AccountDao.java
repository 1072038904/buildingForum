package com.dao.dao.AccountManage;

import com.dao.common.BaseDao;
import com.model.Account;

import java.util.List;

public interface AccountDao extends BaseDao<Account> {
    public List<Account> findAccountByUsername(Account account);
}
