package com.dao.dao.PostManage.imple;

import com.dao.PostManage.PostDao;
import com.dao.common.imple.BaseDaoHibernate4;
import com.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostDaoImple extends BaseDaoHibernate4<Post> implements PostDao {
    @Override
    public List<Post> findAdminPost(Account account,Section sec) {
        List<Post> post= getSessionFactory().getCurrentSession()
                .createQuery("from Post post where post.account=:account and post.section=:sec")
                .setParameter("account",account).setParameter("sec",sec).list();
        return post;
    }

    @Override
    public void addViewNum(Post post) {
        getSessionFactory().getCurrentSession().update(post);
    }

    @Override
    public void updateUserFav(UserInfor userInfor) {
        getSessionFactory().getCurrentSession().update(userInfor);
    }

    @Override
    public void savePostContent(PostContent postContent) {
        getSessionFactory().getCurrentSession().save(postContent);
    }

    @Override
    public List<Post> getAllUserPost(Account account) {
        List<Post> post= getSessionFactory().getCurrentSession()
                .createQuery("from Post post where post.account=:account")
                .setParameter("account",account).list();
        return post;
    }


}
