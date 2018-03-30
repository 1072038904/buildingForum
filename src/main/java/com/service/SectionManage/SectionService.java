package com.service.SectionManage;

import com.model.Account;
import com.model.Post;
import com.model.Section;
import com.model.UserInfor;
import com.service.common.BaseService;
import com.util.PageBean;

import java.util.List;

public interface SectionService extends BaseService<Section>{
    PageBean fuzzyQuerySectionNameByAccount(Account account, String key, Integer currentPage, Integer pageSize);
    void addViewNum(int postId);
    void updateUserFav(UserInfor userInfor);
    Section getSectionById(int sectionId);
    List<Section> fuzzyQuerySectionByName(String sectionNmae);
    int getPostNum(int sectionId);
    List<Post> getPostBySectionIdAndPage(int sectionId, int page, int num);
}
