package com.dao.dao.CommentManage.imple;

import com.dao.CommentManage.CommentDao;
import com.dao.common.imple.BaseDaoHibernate4;
import com.model.Account;
import com.model.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDaoImple extends BaseDaoHibernate4<Comment> implements CommentDao {
    @Override
    public List<Comment> getAllTargetUserComment(Account account) {
        List<Comment> post= getSessionFactory().getCurrentSession()
                .createQuery("from Comment com where com.targetAccount=:account")
                .setParameter("account",account).list();
        return post;
    }
}