package com.dao.dao.SectionManage;

import com.dao.common.BaseDao;
import com.model.Post;
import com.model.Section;
import com.model.UserInfor;

import java.util.List;

public interface SectionDao extends BaseDao<Section> {
    void addViewNum(Section post);
    void updateUserFav(UserInfor userInfor);
    int getPostNum(int sectionId);
    List<Post> getPostBysectionIdAndPage(int sectionId, int start, int num);
}
