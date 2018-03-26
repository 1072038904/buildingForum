package com.service.UserInforManage.imple;

import java.util.List;

import javax.annotation.Resource;


import com.dao.UserInforManage.UserInforDao;
import com.service.UserInforManage.UserInforService;
import com.util.PageBean;
import org.springframework.stereotype.Service;

import com.model.Account;
import com.model.UserInfor;

import com.service.common.imple.BaseServiceImple;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserInforServiceImple extends BaseServiceImple<UserInfor> implements UserInforService{
	
	private UserInforDao userInforDao;
	public UserInforServiceImple() {
		// TODO Auto-generated constructor stub
	}
	public UserInforDao getUserInforDao() {
		return userInforDao;
	}
	@Resource
	public void setUserInforDao(UserInforDao userInforDao) {
		super.setBaseDao(userInforDao);
		this.userInforDao = userInforDao;
	}
	public UserInfor findEnetityByAccount(Account account) {
		// TODO Auto-generated method stub
		//System.out.println("from UserInfor as a where a.account.id ='"+account.getId()+"'");
		List<UserInfor> ple=userInforDao.find("from UserInfor as a where a.account.id ='"+account.getId()+"'");
		for(Object ele:ple){
			return (UserInfor) ele;
		}
		return null;
	}

	@Override
	public PageBean fuzzyQueryUserNameByAccount(Account account, String key, Integer currentPage, Integer pageSize) {
		return null;
	}
}
