package com.dao.dao.SectionManage.imple;

import com.dao.SectionManage.SectionDao;
import com.dao.common.imple.BaseDaoHibernate4;
import com.model.Account;
import com.model.Post;
import com.model.Section;
import com.model.UserInfor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SectionDaoImple extends BaseDaoHibernate4 <Section>implements SectionDao{
    public SectionDaoImple() {
    }

    @Override
    public void addViewNum(Section post) {
        getSessionFactory().getCurrentSession().update(post);
    }

    @Override
    public void updateUserFav(UserInfor userInfor) {
        getSessionFactory().getCurrentSession().update(userInfor);
    }
    @Override
    public int getPostNum(int sectionId) {
        Section sec=new Section();
        sec.setId(sectionId);
        Account account=new Account();
        account.setId(1);
        account.setAccount(1);
        return ((Long)getSessionFactory().getCurrentSession().createQuery
                ("select count(*) from Post as post where post.section=:section and post.isDelete=0 and post.account!=:account")
                .setParameter("section",sec).setParameter("account",account).uniqueResult()).intValue();
    }

    @Override
    public List<Post> getPostBysectionIdAndPage(int sectionId, int start, int num) {
        Section sec=new Section();
        sec.setId(sectionId);
        Account account=new Account();
        account.setId(1);
        account.setAccount(1);
        return getSessionFactory().getCurrentSession().createQuery
                ("from Post as post where post.section=:section and post.isDelete=0 and post.account!=:account")
                .setParameter("section",sec).setParameter("account",account).setFirstResult(start).setMaxResults(num).list();
    }
}
