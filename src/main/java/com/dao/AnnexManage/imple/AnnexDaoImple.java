package com.dao.AnnexManage.imple;

import com.dao.AnnexManage.AnnexDao;
import com.dao.common.imple.BaseDaoHibernate4;
import com.model.Annex;
import org.springframework.stereotype.Repository;

@Repository
public class AnnexDaoImple extends BaseDaoHibernate4 <Annex>implements AnnexDao{
}
