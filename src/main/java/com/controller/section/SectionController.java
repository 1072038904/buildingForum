package com.controller.section;

import com.model.*;
import com.service.BoardManage.BoardService;
import com.service.SectionManage.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/section")
public class SectionController {

    @Autowired
    @Qualifier("sectionServiceImple")
    private SectionService sectionService;
    @Autowired
    @Qualifier("boardServiceImple")
    private BoardService boardService;

    @RequestMapping("/addSection")
    @ResponseBody
    public Map<String, String> addaddSectionSectio(@RequestBody Map<String, String> request) {
        Section sec = new Section();
        sec.setSectionName(request.get("sectionName"));
        Board board = new Board();
        board.setId(Integer.parseInt(request.get("boardId")));
        sec.setBoard(board);
        sectionService.saveNewEntity(sec);
        Map<String, String> map = new HashMap<String, String>();
        map.put("isSuccess","true");
        return map;
    }

    @RequestMapping("/sectionpage")
    public String sectionPage(){
        return "postingdisplay";
    }

    @RequestMapping("/addViewNum")
    @ResponseBody
    public Map<String,String> addViewNum(@RequestBody Map<String,Integer> request){
        sectionService.addViewNum(request.get("sectionId"));
        Map map=new HashMap();
        return map;
    }
    @RequestMapping("/isSectionFav")
    @ResponseBody
    public Map<String,String> isPostFav(@RequestBody Map<String,Integer> request,HttpSession session){
        Map<String,String> map=new HashMap<String,String>();
        Account account=(Account)session.getAttribute("user");
        for(Section post:account.getUserInfor().getFavoriteSection()){
            if(post.getId()==request.get("sectionId")){

                map.put("isFav","true");
                return map;
            }
        }
        map.put("isFav","false");
        return map;
    }
    @RequestMapping("/favBySectionId")
    @ResponseBody
    public Map<String,String> favByPostId(@RequestBody Map<String,Integer> request,HttpSession session){
        Map<String,String> map=new HashMap<String,String>();
        Account account=(Account)session.getAttribute("user");
        Section post=sectionService.getSectionById(request.get("sectionId"));
        account.getUserInfor().getFavoriteSection().add(post);
        sectionService.updateUserFav(account.getUserInfor());
        session.setAttribute("user",account);
        map.put("isSuccess","true");
        return map;
    }

    @RequestMapping("/disFavBySectionId")
    @ResponseBody
    public Map<String,String> disFavByPostId(@RequestBody Map<String,Integer> request,HttpSession session){
        Map<String,String> map=new HashMap<String,String>();
        Account account=(Account)session.getAttribute("user");
        for(Section post:account.getUserInfor().getFavoriteSection()){
            if(post.getId()==request.get("sectionId")){
                account.getUserInfor().getFavoriteSection().remove(post);
                sectionService.updateUserFav(account.getUserInfor());
                break;
            }
        }
        session.setAttribute("user",account);
        map.put("isSuccess","true");
        return map;
    }
    @RequestMapping("/getAllSection")
    @ResponseBody
    public List<Section> getAllSection(){
        List<Board> list=boardService.findAllEntity(Board.class);
        List<Section> newset=new ArrayList<Section>();
        for(int i=0;i<list.size();i++){
            for(Section sec:list.get(i).getSections()){
                Section newsec=new Section();
                newsec.setSectionName(sec.getSectionName());
                newsec.setId(sec.getId());
                newset.add(newsec);
            }
        }
        return newset;
    }

    @RequestMapping("/search")
    @ResponseBody
    public List<Section> search(@RequestBody Map<String,String> request){
        List<Section> list=sectionService.fuzzyQuerySectionByName(request.get("content"));
        List<Section> newset=new ArrayList<Section>();
        for(int i=0;i<list.size();i++){
            Section newsec=new Section();
            newsec.setSectionName(list.get(i).getSectionName());
            newsec.setId(list.get(i).getId());
            newsec.setPostNum(list.get(i).getPostNum());
            newsec.setReadNum(list.get(i).getReadNum());
            Board board=new Board();
            board.setBoardName(list.get(i).getBoard().getBoardName());
            newsec.setBoard(board);
            newset.add(newsec);
        }
        return newset;
    }

    @RequestMapping("/getPostNum")
    @ResponseBody
    public Map<String,Integer> getPostNum(@RequestBody Map<String,Integer> request){
        int num=sectionService.getPostNum(request.get("sectionId"));
        Map<String,Integer> map=new HashMap<>();
        map.put("num",num);
        return map;
    }

    @RequestMapping("/getPostBySectionIdAndPage")
    @ResponseBody
    public List<Post> getPostBySectionIdAndPage(@RequestBody Map<String,Integer> request){
        List<Post> postlist= sectionService.getPostBySectionIdAndPage(request.get("sectionId"),request.get("page"),request.get("numOfPost"));
        List<Post> returnss=new ArrayList<Post>();
        for(int i=0;i<postlist.size();i++){
            if(!postlist.get(i).isDelete()){
                Post post=new Post();
                Section sec=new Section();
                sec.setId(postlist.get(i).getSection().getId());
                post.setSection(sec);
                post.setId(postlist.get(i).getId());
                post.setViewNum(postlist.get(i).getViewNum());
                post.setThumpUpNum(postlist.get(i).getThumpUpNum());
                post.setCommentsnum(postlist.get(i).getCommentsnum());
                post.setDate(postlist.get(i).getDate());
                post.setTitle(postlist.get(i).getTitle());
                Account returns=new Account();
                returns.setAccount(postlist.get(i).getAccount().getAccount());
                returns.setId(postlist.get(i).getAccount().getId());
                returns.setUsername(postlist.get(i).getAccount().getUsername());
                UserInfor userInfor=new UserInfor();
                userInfor.setBalance(postlist.get(i).getAccount().getUserInfor().getBalance());
                userInfor.setCredit(postlist.get(i).getAccount().getUserInfor().getCredit());
                userInfor.setLevel(postlist.get(i).getAccount().getUserInfor().getLevel());
                returns.setUserInfor(userInfor);
                post.setAccount(returns);
                returnss.add(post);
            }
        }
        return returnss;
    }

}
