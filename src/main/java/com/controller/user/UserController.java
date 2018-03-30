package com.controller.user;

import com.model.*;
import com.service.AccountManage.AccountService;
import com.service.AccountManage.LoginService;
import com.service.CommentManage.CommentService;
import com.service.PostManage.PostService;
import com.service.UserInforManage.UserInforService;
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
@RequestMapping("user")
public class UserController {

    @Autowired
    @Qualifier("loginServiceImple")
    private LoginService loginService;
    @Autowired
    @Qualifier("userInforServiceImple")
    private UserInforService userInforService;
    @Autowired
    @Qualifier("accountServiceImple")
    private AccountService accountService;
    @Autowired
    @Qualifier("postServiceImple")
    private PostService postService;
    @Autowired
    @Qualifier("commentServiceImple")
    private CommentService commentService;

    @RequestMapping("/mainpage")
    public String toMainpage(){
        return "mainpage";
    }

    @RequestMapping("/login")
    @ResponseBody
    public Map<String,String> login(@RequestBody Account account,HttpSession session){
        Map<String,String> map=new HashMap<String,String>();
        if(loginService.isValid(account).getId()!=null){
            map.put("isSuccess","true");
            session.setAttribute("user",loginService.isValid(account));
        }
        else map.put("isSuccess","false");
        return map;
    }

    @RequestMapping("/isLogin")
    @ResponseBody
    public Account isLogin(HttpSession session){
        Account account=(Account)session.getAttribute("user");
        Account returns=new Account();
        returns.setAccount(account.getAccount());
        returns.setId(account.getId());
        returns.setUsername(account.getUsername());
        UserInfor userInfor=new UserInfor();
        userInfor.setEmail(account.getUserInfor().getEmail());
        userInfor.setHobby(account.getUserInfor().getHobby());
        userInfor.setTelephone(account.getUserInfor().getTelephone());
        userInfor.setSex(account.getUserInfor().getSex());
        userInfor.setBalance(account.getUserInfor().getBalance());
        userInfor.setCredit(account.getUserInfor().getCredit());
        userInfor.setLevel(account.getUserInfor().getLevel());
        returns.setUserInfor(userInfor);
        return returns;
    }
    @RequestMapping("/exitLogin")
    @ResponseBody
    public Map<String,String> exitLogin(HttpSession session){
        session.removeAttribute("user");
        Map<String,String> map=new HashMap<String,String>();
        map.put("isSuccess","true");
        return map;
    }


    @RequestMapping("/hasNotReadComment")
    @ResponseBody
    public Map<String,Integer> hasNotReadComment(HttpSession session){
        Account account=(Account)session.getAttribute("user");
        int num=0;
        for(Comment com:account.getComment()){
            if(com.isRead()==false){
                num++;
            }
        }
        Map<String,Integer> map=new HashMap<String,Integer>();
        map.put("num",num);
        return map;
    }

    @RequestMapping("/personpage")
    public String toPersonpage(){
        return "personpage";
    }

    @RequestMapping("/updateUserInfor")
    @ResponseBody
    public Map<String,String> updateUserInfor(HttpSession session,@RequestBody Map<String,String> request){
        Account account=(Account)session.getAttribute("user");
        account.setPassword(request.get("password"));
        account.getUserInfor().setHobby(request.get("hobby"));
        account.getUserInfor().setEmail(request.get("email"));
        account.getUserInfor().setTelephone(request.get("telephone"));
        account.getUserInfor().setSex("1");
        userInforService.updateEntity(account.getUserInfor());
        userInforService.saveAccount(account);
        Map<String,String> map=new HashMap<String,String>();
        map.put("isSuccess","true");
        return map;
    }

    @RequestMapping("/getAllUserPost")
    @ResponseBody
    public List<Post> getAllUserPost(HttpSession session){
        List<Post> postlist= postService.getAllUserPost((Account)session.getAttribute("user"));
        List<Post> returnss=new ArrayList<Post>();
        for(int i=0;i<postlist.size();i++){
            if(!postlist.get(i).isDelete()){
                Post post=new Post();
                post.setId(postlist.get(i).getId());
                post.setViewNum(postlist.get(i).getViewNum());
                post.setThumpUpNum(postlist.get(i).getThumpUpNum());
                post.setCommentsnum(postlist.get(i).getCommentsnum());
                post.setDate(postlist.get(i).getDate());
                post.setTitle(postlist.get(i).getTitle());
                Section sec=new Section();
                sec.setId(postlist.get(i).getSection().getId());
                post.setSection(sec);
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

    @RequestMapping("/getAllDeletePost")
    @ResponseBody
    public List<Post> getAllDeletePost(HttpSession session) {
        List<Post> postlist = postService.getAllUserPost((Account) session.getAttribute("user"));
        List<Post> returnss = new ArrayList<Post>();
        for (int i = 0; i < postlist.size(); i++) {
            if (postlist.get(i).isDelete()) {
                Post post = new Post();
                Section sec=new Section();
                sec.setId(postlist.get(i).getSection().getId());
                post.setSection(sec);
                post.setId(postlist.get(i).getId());
                post.setViewNum(postlist.get(i).getViewNum());
                post.setThumpUpNum(postlist.get(i).getThumpUpNum());
                post.setCommentsnum(postlist.get(i).getCommentsnum());
                post.setDate(postlist.get(i).getDate());
                post.setTitle(postlist.get(i).getTitle());
                post.setValid(postlist.get(i).isValid());
                Account returns = new Account();
                returns.setAccount(postlist.get(i).getAccount().getAccount());
                returns.setId(postlist.get(i).getAccount().getId());
                returns.setUsername(postlist.get(i).getAccount().getUsername());
                UserInfor userInfor = new UserInfor();
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
    @RequestMapping("/huanyuanPost")
    @ResponseBody
    public Map<String,String> huanyuanPost(@RequestBody Map<String,Integer> request){
        Post post=postService.findEnetityById(request.get("postId"),Post.class);
        post.setDelete(false);
        postService.updateEntity(post);
        Map<String,String> map=new HashMap<String,String>();
        map.put("isSuccess","true");
        return map;
    }

    @RequestMapping("/getAllCommentByUser")
    @ResponseBody
    public List<Comment> getAllCommentByUser(HttpSession session) {
        List<Comment> commentlist = commentService.getAllTargetUserComment((Account) session.getAttribute("user"));
        List<Comment> returnss = new ArrayList<Comment>();
        for (int i = 0; i < commentlist.size(); i++) {
            Comment com=new Comment();
            com.setId(commentlist.get(i).getId());
            com.setContent(commentlist.get(i).getContent());
            com.setRead(commentlist.get(i).isRead());
            com.setDate(commentlist.get(i).getDate());
            Account account=new Account();
            account.setId(commentlist.get(i).getAccount().getId());
            account.setUsername(commentlist.get(i).getAccount().getUsername());
            com.setAccount(account);
            Post post=new Post();
            post.setId(commentlist.get(i).getPost().getId());
            Section sec=new Section();
            sec.setId(commentlist.get(i).getPost().getSection().getId());
            post.setSection(sec);
            com.setPost(post);
            returnss.add(com);
        }
        return returnss;
    }

    @RequestMapping("/searchResult")
    public String toSearchResult(){
        return "searchresult";
    }

    @RequestMapping("/search")
    @ResponseBody
    public List<Account> search(@RequestBody Map<String,Object> request){
        List<Account> returnss=accountService.fuzzyQueryUserName((String)request.get("content"));
        List<Account> accountlist=new ArrayList<Account>();
        for(int i=0;i<returnss.size();i++){
            Account returns=new Account();
            returns.setAccount(returnss.get(i).getAccount());
            returns.setId(returnss.get(i).getId());
            returns.setUsername(returnss.get(i).getUsername());
            UserInfor userInfor=new UserInfor();
            userInfor.setEmail(returnss.get(i).getUserInfor().getEmail());
            userInfor.setHobby(returnss.get(i).getUserInfor().getHobby());
            userInfor.setTelephone(returnss.get(i).getUserInfor().getTelephone());
            userInfor.setSex(returnss.get(i).getUserInfor().getSex());
            userInfor.setBalance(returnss.get(i).getUserInfor().getBalance());
            userInfor.setCredit(returnss.get(i).getUserInfor().getCredit());
            userInfor.setLevel(returnss.get(i).getUserInfor().getLevel());
            returns.setUserInfor(userInfor);
            accountlist.add(returns);
        }
        return accountlist;
    }
    @RequestMapping("/peopleRank")
    public String toPeopleRank(){
        return "peoplerank";
    }

    @RequestMapping("/getRank")
    @ResponseBody
    public List<Integral> getRank(){
        List<Integral> no=userInforService.getALLIntegral();
        List<Integral> newIntegral=new ArrayList<Integral>();
        for(int i=0;i<no.size();i++){
            Account acc=new Account();
            acc.setAccount(no.get(i).getAccount().getAccount());
            acc.setId(no.get(i).getAccount().getId());
            acc.setUsername(no.get(i).getAccount().getUsername());
            UserInfor userInfor=new UserInfor();
            userInfor.setEmail(no.get(i).getAccount().getUserInfor().getEmail());
            userInfor.setHobby(no.get(i).getAccount().getUserInfor().getHobby());
            userInfor.setTelephone(no.get(i).getAccount().getUserInfor().getTelephone());
            userInfor.setPostNum(no.get(i).getAccount().getPost().size());
            userInfor.setSex(no.get(i).getAccount().getUserInfor().getSex());
            userInfor.setBalance(no.get(i).getAccount().getUserInfor().getBalance());
            userInfor.setCredit(no.get(i).getAccount().getUserInfor().getCredit());
            userInfor.setLevel(no.get(i).getAccount().getUserInfor().getLevel());
            acc.setUserInfor(userInfor);
            Integral inte=new Integral();
            inte.setAccount(acc);
            //inte.setZanNum(no.get(i).getZanNum());
           // inte.setHuifuNum(no.get(i).getHuifuNum());
            newIntegral.add(inte);
        }
        return newIntegral;
    }
}
