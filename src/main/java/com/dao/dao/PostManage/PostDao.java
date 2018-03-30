package com.dao.dao.PostManage;

import com.dao.common.BaseDao;
import com.model.*;

import java.util.List;

public interface PostDao extends BaseDao<Post> {
    List<Post> findAdminPost(Account account, Section sec);
    void addViewNum(Post post);
    void updateUserFav(UserInfor userInfor);
    void savePostContent(PostContent postContent);
    List<Post> getAllUserPost(Account account);

}
