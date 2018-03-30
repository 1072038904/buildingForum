package com.service.AccountManage.imple;

import com.dao.AccountManage.AccountDao;
import com.model.Account;
import com.service.AccountManage.AccountService;
import com.service.common.imple.BaseServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountServiceImple extends BaseServiceImple<Account> implements AccountService {
    @Autowired
    @Qualifier("accountDaoImple")
    private AccountDao accountDao;
    @Override
    public boolean isExist(Account account) {
        if(accountDao.findAccountByUsername(account).size()==0)
            return false;
        else return true;
    }
    @Override
    public List<Account> fuzzyQueryUserName(String fuzzyName) {
        List<Account> resultList = accountDao.find("from Account as a where a.username like '%"+fuzzyName+"%'");
        return resultList;
    }
}
