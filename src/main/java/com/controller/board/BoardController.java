package com.controller.board;

import com.model.Board;
import com.model.Section;
import com.service.BoardManage.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    @Qualifier("boardServiceImple")
    private BoardService boardService;

    @RequestMapping("/getAllBoard")
    @ResponseBody
    public List<Board> getAllGoods(){
        List<Board> list=boardService.findAllEntity(Board.class);
        for(int i=0;i<list.size();i++){
            List<Section> newset=new ArrayList<Section>();
            for(Section sec:list.get(i).getSections()){
                sec.setBoard(null);
                sec.setPostSet(null);
                newset.add(sec);
            }
            list.get(i).setSections(newset);
        }
        return list;
    }
}
