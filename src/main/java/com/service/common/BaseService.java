package com.service.common;

import java.util.List;

import com.model.Account;
import com.util.PageBean;


public interface BaseService<T> {
	
void saveNewEntity(T entity);
/**
 * 返回 1即是存在 
 * 返回 2即是不存在
 * @param entityClazz
 * @return
 */
List <T>findAllEntity(Class<T> entityClazz);
public T findEnetityById(Integer id, Class<T> entityClazz);
void deleteEntity(T entity);
void updateEntity(T entity);
PageBean findEntityByPage(Class<T> entityClazz, Integer currentPage, Integer pageSize);
PageBean findEntityPageByName(String name, Class<T> entityClazz, Integer currentPage, Integer pageSize);
public PageBean findEntityPageById(String name, Class<T> entityClazz, Integer currentPage, Integer pageSize);
T findEnetityByAccount(Account account, Class<T> entityClazz);
PageBean findEntityPageByAccount(Account account, Class<T> entityClazz, Integer currentPage, Integer pageSize);
PageBean findEntityAllPage(Class<T> entityClazz, Integer currentPage, Integer pageSize);
void deleteObByCommodity(Class<T> entityClazz, String Commodity_id);
/*
模糊查询参数绑定通用
 */
PageBean fuzzyQueryNameByAccount(Account account, Class<T> entityClazz, String key, Integer currentPage, Integer pageSozeq);
}
