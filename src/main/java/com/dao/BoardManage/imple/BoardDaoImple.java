package com.dao.BoardManage.imple;

import com.dao.BoardManage.BoardDao;
import com.dao.common.imple.BaseDaoHibernate4;
import com.model.Board;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDaoImple extends BaseDaoHibernate4 <Board>implements BoardDao {
}
