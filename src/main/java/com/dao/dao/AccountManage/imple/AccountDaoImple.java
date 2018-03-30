package com.dao.dao.AccountManage.imple;

import com.dao.AccountManage.AccountDao;
import com.dao.common.imple.BaseDaoHibernate4;
import com.model.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountDaoImple extends BaseDaoHibernate4<Account> implements AccountDao {
    @Override
    public List<Account> findAccountByUsername(Account account) {
        return getSessionFactory().getCurrentSession().createQuery("from Account account where account.username=:username")
                .setParameter("username",account.getUsername()).list();
    }
}
