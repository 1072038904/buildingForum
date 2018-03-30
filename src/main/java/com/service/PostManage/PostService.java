package com.service.PostManage;

import com.model.Account;
import com.model.Post;
import com.model.PostContent;
import com.model.UserInfor;
import com.service.common.BaseService;
import com.util.PageBean;

import java.util.List;

public interface PostService extends BaseService<Post>{
    PageBean fuzzyQuerySectionNameByAccount(Account account, String key, Integer currentPage, Integer pageSize);
    List<Post> findAdminPost(String adminname, int sectionId);
    void addViewNum(int postId);
    void updateUserFav(UserInfor userInfor);
    void savePostContent(PostContent postContent);
    Post recommendPost(UserInfor userInfor);
    List<Post> getAllUserPost(Account account);
    List<Post> fuzzyQueryPostByTitle(String title);
}
