package com.service.CommentManage;

import com.model.Account;
import com.model.Comment;
import com.service.common.BaseService;
import com.util.PageBean;

import java.util.List;

public interface CommentService extends BaseService<Comment> {
    PageBean fuzzyQuerySectionNameByAccount(Account account, String key, Integer currentPage, Integer pageSize);
    List<Comment> getAllTargetUserComment(Account account);
}
