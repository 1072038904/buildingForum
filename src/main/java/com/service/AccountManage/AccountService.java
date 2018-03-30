package com.service.AccountManage;

import com.model.Account;
import com.service.common.BaseService;

import java.util.List;

public interface AccountService extends BaseService<Account> {
    public boolean isExist(Account account);
    List<Account> fuzzyQueryUserName(String fuzzyName);
}
