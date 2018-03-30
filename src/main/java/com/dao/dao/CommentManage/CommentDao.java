package com.dao.dao.CommentManage;

import com.dao.common.BaseDao;
import com.model.Account;
import com.model.Comment;

import java.util.List;

public interface CommentDao extends BaseDao<Comment> {
    List<Comment> getAllTargetUserComment(Account account);
}
