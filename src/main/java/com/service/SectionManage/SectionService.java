package com.service.SectionManage;

import com.model.Account;
import com.model.Section;
import com.service.common.BaseService;
import com.util.PageBean;

public interface SectionService extends BaseService<Section>{
    PageBean fuzzyQuerySectionNameByAccount(Account account, String key, Integer currentPage, Integer pageSize);

}
