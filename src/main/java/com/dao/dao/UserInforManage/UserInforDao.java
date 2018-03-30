package com.dao.dao.UserInforManage;
import com.dao.common.BaseDao;
import com.model.Account;
import com.model.UserInfor;
public interface UserInforDao extends BaseDao<UserInfor>{
void saveAccount(Account account);

}
