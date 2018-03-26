package com.service.SectionManage.imple;

import com.dao.SectionManage.SectionDao;
import com.model.Account;
import com.model.Board;
import com.model.Section;
import com.service.SectionManage.SectionService;
import com.service.common.BaseService;
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


}
