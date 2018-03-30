package com.service.UserInforManage;

import com.model.Account;
import com.model.Integral;
import com.model.UserInfor;
import com.service.common.BaseService;
import com.util.PageBean;

import java.util.List;

public interface UserInforService extends BaseService<UserInfor> {
	UserInfor findEnetityByAccount(Account account);
	PageBean fuzzyQueryUserNameByAccount(Account account, String key, Integer currentPage, Integer pageSize);
	void saveAccount(Account accout);


	Integral coountIntegral(Account account);
	List<Integral> getALLIntegral();
}
