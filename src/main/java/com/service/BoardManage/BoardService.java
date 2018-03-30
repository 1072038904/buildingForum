package com.service.BoardManage;

import com.model.Account;
import com.model.Board;
import com.service.common.BaseService;
import com.util.PageBean;

public interface BoardService extends BaseService<Board> {
    PageBean fuzzyQueryBoardNameByAccount(Account account, String key, Integer currentPage, Integer pageSize);
    /*
    获取所有board以及board下属的section信息不需要post信息
    * */
    PageBean obtainAllBoardOfSection(Integer currentPage, Integer pageSize);
}
