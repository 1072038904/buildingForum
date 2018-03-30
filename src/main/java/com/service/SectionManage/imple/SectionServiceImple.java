package com.service.SectionManage.imple;

import com.dao.SectionManage.SectionDao;
import com.model.Account;
import com.model.Post;
import com.model.Section;
import com.model.UserInfor;
import com.service.SectionManage.SectionService;
import com.service.common.imple.BaseServiceImple;
import com.util.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class SectionServiceImple extends BaseServiceImple<Section> implements SectionService{
    private SectionDao sectionDao;

    public SectionDao getSectionDao() {
        return sectionDao;
    }
    @Resource
    public void setSectionDao(SectionDao sectionDao) {
        super.setBaseDao(sectionDao);
        this.sectionDao = sectionDao;
    }

    @Override
    public PageBean fuzzyQuerySectionNameByAccount(Account account, String key, Integer currentPage, Integer pageSize) {
        // TODO Auto-generated method stub
        PageBean pageBean = new PageBean();
        //Set Parameters totalRows
        Long totalRows = sectionDao.findCount(Section.class);
        pageBean.setAllRow(totalRows);
        pageBean.setCurrentPage(currentPage.intValue());
        pageBean.setPageSize(pageSize.intValue());
        //Initialize PageBean
        pageBean.init();
        //get the resultSet
        List<Section> resultList= sectionDao.findByPage("from Section as a where a.sectionName like '%"+key+"%'",currentPage,pageSize);
        pageBean.setList(resultList);
        return pageBean;
    }
    @Override
    public void addViewNum(int postId) {
        Section section=sectionDao.findEntityById(postId,Section.class);
        section.setReadNum(section.getReadNum()+1);
        sectionDao.addViewNum(section);
    }

    @Override
    public void updateUserFav(UserInfor userInfor) {
        sectionDao.updateUserFav(userInfor);
    }

    @Override
    public Section getSectionById(int sectionId) {
        return sectionDao.findEntityById(sectionId,Section.class);
    }
    @Override
    public List<Section> fuzzyQuerySectionByName(String sectionNmae) {
        List <Section> resultList = sectionDao.find("from Section as a where a.sectionName like '%"+sectionNmae+"%'");
        return resultList;
    }

    @Override
    public int getPostNum(int sectionId) {
        return sectionDao.getPostNum(sectionId);
    }

    @Override
    public List<Post> getPostBySectionIdAndPage(int sectionId, int page, int num) {
        int total=getPostNum(sectionId);
        int start=(page-1)*num,nums;
        if(total>=page*num){
            nums=num;
        }
        else{
            nums=total-(page-1)*num;
        }
        return sectionDao.getPostBysectionIdAndPage(sectionId,start,nums);
    }
}
