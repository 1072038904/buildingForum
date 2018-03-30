package com.service.UserInforManage.imple;

import com.dao.CommentManage.CommentDao;
import com.dao.PostManage.PostDao;
import com.dao.UserInforManage.IntegralDao;
import com.dao.UserInforManage.UserInforDao;
import com.model.*;
import com.service.UserInforManage.UserInforService;
import com.service.common.imple.BaseServiceImple;
import com.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class UserInforServiceImple extends BaseServiceImple<UserInfor> implements UserInforService{
	
	private UserInforDao userInforDao;
	@Autowired
	private IntegralDao integralDao;
	@Autowired
	private PostDao postDao;
	@Autowired
	private CommentDao commentDao;
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

	@Override
	public void saveAccount(Account account) {
		userInforDao.saveAccount(account);
	}


	@Override
	public Integral coountIntegral(Account account) {
		List<Integral> integrals = integralDao.find("from Integral a where a.account_id ='"+account.getId()+"'");
		List<Post> posts = postDao.find("from Post as a where a.id ='" + account.getId() + "'");
		int postNum = posts.size();
		List<Comment> comments = commentDao.find("from Comment as a wherr a.id ='" + account.getId() + "'");
		int commentsNum = comments.size();
		int integralNum = postNum * 3 + commentsNum * 4;
		if (integrals.size()==0) {
			Integral integral = new Integral();
			integral.setIntegralNum(integralNum);
			integral.setAccount(account);
			integralDao.save(integral);
			return integral;
		}
		else{
			for(Object ele:integrals){
				Integral integral = (Integral)ele;
				integral.setIntegralNum(integralNum);
				integralDao.update(integral);
				return integral;
			}
		}
			return null;
	}

	@Override
	public List<Integral> getALLIntegral() {
		List<Integral> integralList = integralDao.find("from Integral order by integralNum desc,account_id asc");
		return integralList;
	}

}
