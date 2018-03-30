package com.service.BoardManage.imple;

import com.dao.BoardManage.BoardDao;
import com.model.Account;
import com.model.Board;
import com.service.BoardManage.BoardService;
import com.service.common.imple.BaseServiceImple;
import com.util.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class BoardServiceImple extends BaseServiceImple<Board> implements BoardService{
    private BoardDao boardDao;
    public BoardDao getBoardDao() {
        return boardDao;
    }
    @Resource
    public void setBoardDao(BoardDao boardDao) {
        super.setBaseDao(boardDao);
        this.boardDao = boardDao;
    }

    @Override
    public PageBean fuzzyQueryBoardNameByAccount(Account account, String key, Integer currentPage, Integer pageSize) {
        // TODO Auto-generated method stub
        PageBean pageBean = new PageBean();
        //Set Parameters totalRows
        Long totalRows = boardDao.findCount(Board.class);
        pageBean.setAllRow(totalRows);
        pageBean.setCurrentPage(currentPage.intValue());
        pageBean.setPageSize(pageSize.intValue());
        //Initialize PageBean
        pageBean.init();
        //get the resultSet
        List<Board> resultList= boardDao.findByPage("from Board as a where a.boardName like '%"+key+"%'",currentPage,pageSize);
        pageBean.setList(resultList);
        return pageBean;
    }

    @Override
    public PageBean obtainAllBoardOfSection(Integer currentPage,Integer pageSize) {
        // TODO Auto-generated method stub
        System.out.println("123");
        PageBean pageBean = new PageBean();
        //Set Parameters totalRows
        Long totalRows = boardDao.findCount(Board.class);
        pageBean.setAllRow(totalRows);
        pageBean.setCurrentPage(currentPage.intValue());
        pageBean.setPageSize(pageSize.intValue());
        //Initialize PageBean
        pageBean.init();
        //get the resultSet
      //  List<Board> resultList= boardDao.findByPage("from Board as a where a.boardName like '%"+key+"%'",currentPage,pageSize);
        List <Board> resultList = boardDao.findByPage("from Board",currentPage,pageSize);
        System.out.println("shu"+resultList.size());
        pageBean.setList(resultList);
        return pageBean;
    }
}
