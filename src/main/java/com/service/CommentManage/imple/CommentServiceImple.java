package com.service.CommentManage.imple;

import com.dao.CommentManage.CommentDao;
import com.model.Account;
import com.model.Comment;
import com.service.CommentManage.CommentService;
import com.service.common.imple.BaseServiceImple;
import com.util.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class CommentServiceImple extends BaseServiceImple<Comment> implements CommentService {
    private CommentDao commentDao;

    public CommentDao getCommentDao() {
        return commentDao;
    }
    @Resource
    public void setCommentDao(CommentDao commentDao) {
        super.setBaseDao(commentDao);
        this.commentDao = commentDao;
    }

    @Override
    public PageBean fuzzyQuerySectionNameByAccount(Account account, String key, Integer currentPage, Integer pageSize) {
        // TODO Auto-generated method stub
        PageBean pageBean = new PageBean();
        //Set Parameters totalRows
        Long totalRows = commentDao.findCount(Comment.class);
        pageBean.setAllRow(totalRows);
        pageBean.setCurrentPage(currentPage.intValue());
        pageBean.setPageSize(pageSize.intValue());
        //Initialize PageBean
        pageBean.init();
        //get the resultSet
        List<Comment> resultList= commentDao.findByPage("from Section as a where a.sectionName like '%"+key+"%'",currentPage,pageSize);
        pageBean.setList(resultList);
        return pageBean;
    }

    @Override
    public List<Comment> getAllTargetUserComment(Account account) {
       return commentDao.getAllTargetUserComment(account);
    }


}
