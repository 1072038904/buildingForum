package com.dao.SectionManage.imple;

import com.dao.SectionManage.SectionDao;
import com.dao.common.imple.BaseDaoHibernate4;
import com.model.Section;
import org.springframework.stereotype.Repository;

@Repository
public class SectionDaoImple extends BaseDaoHibernate4 <Section>implements SectionDao{
    public SectionDaoImple() {
    }
}
