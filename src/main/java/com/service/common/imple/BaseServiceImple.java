package com.service.common.imple;

import com.dao.common.BaseDao;
import com.model.Account;
import com.service.common.BaseService;
import com.util.GeneratedHql;
import com.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public class BaseServiceImple <T>implements BaseService<T> {
	@Autowired
	@Qualifier("baseDaoHibernate4")
	private BaseDao<T> baseDao;
	public BaseServiceImple() {
		// TODO Auto-generated constructor stub
	}
	
	public BaseDao<T> getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;	
	}

	@Override
	public void saveNewEntity(T entity) {
		// TODO Auto-generated method stub
		baseDao.save(entity);
	}
	@Override
	public List<T> findAllEntity(Class<T> entityClazz) {
		// TODO Auto-generated method stub
		List<T> ple=baseDao.findAll(entityClazz);
		return ple;
	}

	@Override
	public void deleteEntity(T entity) {
		// TODO Auto-generated method stub
		baseDao.delete(entity);
		
	}

	@Override
	public void updateEntity(T entity) {
		// TODO Auto-generated method stub
		baseDao.update(entity);
	}

	@Override
	public PageBean findEntityByPage(Class<T> entityClazz,Integer currentPage, Integer pageSize) {
		// TODO Auto-generated method stub
		PageBean pageBean = new PageBean();
	    //Set Parameters totalRows
	    Long totalRows = baseDao.findCount(entityClazz);
	    pageBean.setAllRow(totalRows);
	    pageBean.setCurrentPage(currentPage.intValue());
	    pageBean.setPageSize(pageSize.intValue()); 
	    //Initialize PageBean
	    pageBean.init();
	    //get the resultSet
	    List<T> resultList = baseDao.findByPage("from "+ entityClazz.getSimpleName(), currentPage, pageSize);
	    pageBean.setList(resultList);
	    return pageBean;
	}

	@Override
	public PageBean findEntityPageByName(String name,Class<T> entityClazz,Integer currentPage, Integer pageSize) {
		// TODO Auto-generated method stub
		PageBean pageBean = new PageBean();
	    //Set Parameters totalRows
	    Long totalRows = baseDao.findCount(entityClazz);
	    pageBean.setAllRow(totalRows);
	    pageBean.setCurrentPage(currentPage.intValue());
	    pageBean.setPageSize(pageSize.intValue()); 
	    //Initialize PageBean
	    pageBean.init();
	    //get the resultSet
	    List<T> resultList = baseDao.findByPage(GeneratedHql.genHql(entityClazz.getSimpleName(), "name",name), currentPage, pageSize);
	    pageBean.setList(resultList);
	    return pageBean;
	}
	@Override
	public PageBean findEntityPageById(String name,Class<T> entityClazz,Integer currentPage, Integer pageSize) {
		// TODO Auto-generated method stub
		PageBean pageBean = new PageBean();
	    //Set Parameters totalRows
	    Long totalRows = baseDao.findCount(entityClazz);
	    pageBean.setAllRow(totalRows);
	    pageBean.setCurrentPage(currentPage.intValue());
	    pageBean.setPageSize(pageSize.intValue()); 
	    //Initialize PageBean
	    pageBean.init();
	    //get the resultSet
	    List<T> resultList = baseDao.findByPage(GeneratedHql.genHql(entityClazz.getSimpleName(), "id",name), currentPage, pageSize);
	    pageBean.setList(resultList);
	    return pageBean;
	}

	@Override//有问题
	public T findEnetityByAccount(Account account,Class<T> entityClazz) {
		// TODO Auto-generated method stub
		System.out.println("from "+entityClazz.getSimpleName()+" as a where a.account.id ='"+account.getId()+"'");
		List<T> ple=baseDao.find("from "+entityClazz.getSimpleName()+" as a where a.account.id ='"+account.getId()+"'");
		for(Object ele:ple){
			@SuppressWarnings("unchecked")
			T t=(T) ele;
			return t;
		}
		return null;
	}
	@Override
	public T findEnetityById(Integer id,Class<T> entityClazz) {
		// TODO Auto-generated method stub
		//System.out.println("from "+entityClazz.getSimpleName()+" as a a.account.account ='"+account.getAccount()+"'");
		T entity=findEnetityById(id,entityClazz);
		return entity;
	}

	@Override
	public PageBean findEntityPageByAccount(Account account, Class<T> entityClazz, Integer currentPage,
			Integer pageSize) {
		// TODO Auto-generated method stub
		PageBean pageBean = new PageBean();
	    //Set Parameters totalRows
	    Long totalRows = baseDao.findCount(entityClazz);
	    pageBean.setAllRow(totalRows);
	    pageBean.setCurrentPage(currentPage.intValue());
	    pageBean.setPageSize(pageSize.intValue()); 
	    //Initialize PageBean
	    pageBean.init();
	    //get the resultSet
	    List<T> resultList = baseDao.findByPage("from "+entityClazz.getSimpleName()+" as a where a.account.id ='"+account.getId()+"'"
	    		, currentPage, pageSize);
	    pageBean.setList(resultList);
	    return pageBean;
	}

	@Override
	public void deleteObByCommodity(Class<T> entityClazz, String commodity_id) {
		// TODO Auto-generated method stub
		List<T> ple = baseDao.find("from "+entityClazz.getSimpleName()+" as a where a.commodity.id ='"+commodity_id+"'");
		for(Object ele:ple){
			T evaluate=(T) ele;
			baseDao.delete(evaluate);
		}
	}

	@Override
	public PageBean findEntityAllPage(Class<T> entityClazz, Integer currentPage, Integer pageSize) {
		// TODO Auto-generated method stub
		PageBean pageBean = new PageBean();
	    //Set Parameters totalRows
	    Long totalRows = baseDao.findCount(entityClazz);
	    pageBean.setAllRow(totalRows);
	    pageBean.setCurrentPage(currentPage.intValue());
	    pageBean.setPageSize(pageSize.intValue()); 
	    //Initialize PageBean
	    pageBean.init();
	    //get the resultSet
	    List<T> resultList = baseDao.findByPage("from "+entityClazz.getSimpleName()
	    		, currentPage, pageSize);
	    pageBean.setList(resultList);
	    return pageBean;
	}

	@Override
	public PageBean fuzzyQueryNameByAccount(Account account, Class<T> entityClazz, String key, Integer currentPage, Integer pageSize) {
		PageBean pageBean = new PageBean();
		Long totalRows = baseDao.findCount(entityClazz);
		pageBean.setAllRow(totalRows);
		pageBean.setCurrentPage(currentPage.intValue());
		pageBean.setPageSize(pageSize.intValue());
		pageBean.init();
	//	List<T> resultList=baseDao.findByPage("form"+entityClazz.getSimpleName(),currentPage,pageSize);
		return null;
	}
}
