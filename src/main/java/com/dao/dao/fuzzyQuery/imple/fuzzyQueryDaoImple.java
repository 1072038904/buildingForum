package com.dao.dao.fuzzyQuery.imple;

import com.dao.common.BaseDao;
import com.dao.common.imple.BaseDaoHibernate4;
import com.dao.fuzzyQuery.fuzzyQueryDao;
import org.springframework.stereotype.Repository;

@Repository
public class fuzzyQueryDaoImple extends BaseDaoHibernate4 implements fuzzyQueryDao {
    public fuzzyQueryDaoImple() {
    }
}
