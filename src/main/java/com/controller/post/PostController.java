package com.controller.post;

import com.model.*;
import com.service.PostManage.PostService;
import com.service.SectionManage.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    @Qualifier("postServiceImple")
    private PostService postService;
    @Autowired
    @Qualifier("sectionServiceImple")
    private SectionService sectionService;


    @RequestMapping("/postDetail")
    public String postDetail(){
        return "postingdetail";
    }

    @RequestMapping("/getAdminPost")
    @ResponseBody
    public List<Post> getAdminPostBySectionId(@RequestBody Map<String,Integer> request){
        List<Post> postlist= postService.findAdminPost("admin",request.get("sectionId"));
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

    @RequestMapping("/getPostById")
    @ResponseBody
    public Post getPostById(@RequestBody Map<String,Integer> request){
        Post yuan=postService.findEnetityById(request.get("postId"),Post.class);
        Post post=new Post();
        post.setId(yuan.getId());
        post.setViewNum(yuan.getViewNum());
        post.setThumpUpNum(yuan.getThumpUpNum());
        post.setCommentsnum(yuan.getCommentsnum());
        post.setPostContent(yuan.getPostContent());
        post.setDate(yuan.getDate());
        post.setTitle(yuan.getTitle());
        post.setDelete(yuan.isDelete());
        Account returns=new Account();
        returns.setAccount(yuan.getAccount().getAccount());
        returns.setId(yuan.getAccount().getId());
        returns.setUsername(yuan.getAccount().getUsername());
        UserInfor userInfor=new UserInfor();
        userInfor.setPostNum(yuan.getAccount().getPost().size());
        userInfor.setBalance(yuan.getAccount().getUserInfor().getBalance());
        userInfor.setCredit(yuan.getAccount().getUserInfor().getCredit());
        userInfor.setLevel(yuan.getAccount().getUserInfor().getLevel());
        returns.setUserInfor(userInfor);
        post.setAccount(returns);
        List<Comment> returnCom=new ArrayList<Comment>();
        for(int i=0;i<yuan.getComments().size();i++){
            Comment com=new Comment();
            com.setToPost(yuan.getComments().get(i).isToPost());
            com.setReplyRank(yuan.getComments().get(i).getReplyRank());
            com.setDate(yuan.getComments().get(i).getDate());
            com.setId(yuan.getComments().get(i).getId());
            com.setContent(yuan.getComments().get(i).getContent());
            Account from=new Account();
            from.setAccount(yuan.getComments().get(i).getAccount().getAccount());
            from.setId(yuan.getComments().get(i).getAccount().getId());
            from.setUsername(yuan.getComments().get(i).getAccount().getUsername());
            UserInfor fromInfor=new UserInfor();
            fromInfor.setBalance(yuan.getComments().get(i).getAccount().getUserInfor().getBalance());
            fromInfor.setCredit(yuan.getComments().get(i).getAccount().getUserInfor().getCredit());
            fromInfor.setLevel(yuan.getComments().get(i).getAccount().getUserInfor().getLevel());
            fromInfor.setPostNum(yuan.getComments().get(i).getAccount().getPost().size());
            from.setUserInfor(fromInfor);
            com.setAccount(from);
            Account target=new Account();
            target.setUsername(yuan.getComments().get(i).getTargetAccount().getUsername());
            com.setTargetAccount(target);
            returnCom.add(com);
        }
        post.setComments(returnCom);
        return post;
    }

    @RequestMapping("/addViewNum")
    @ResponseBody
    public Map<String,String> addViewNum(@RequestBody Map<String,Integer> request){
        postService.addViewNum(request.get("postId"));
        Map map=new HashMap();
        return map;
    }
    @RequestMapping("/isPostFav")
    @ResponseBody
    public Map<String,String> isPostFav(@RequestBody Map<String,Integer> request,HttpSession session){
        Map<String,String> map=new HashMap<String,String>();
        Account account=(Account)session.getAttribute("user");
        for(Post post:account.getUserInfor().getFavoritePosting()){
            if(post.getId()==request.get("postId")){

                map.put("isFav","true");
                return map;
            }
        }
        map.put("isFav","false");
        return map;
    }
    @RequestMapping("/favByPostId")
    @ResponseBody
    public Map<String,String> favByPostId(@RequestBody Map<String,Integer> request,HttpSession session){
        Map<String,String> map=new HashMap<String,String>();
        Account account=(Account)session.getAttribute("user");
        Post post=new Post();
        post.setId(request.get("postId"));
        account.getUserInfor().getFavoritePosting().add(post);
        postService.updateUserFav(account.getUserInfor());
        session.setAttribute("user",account);
        map.put("isSuccess","true");
        return map;
    }

    @RequestMapping("/disFavByPostId")
    @ResponseBody
    public Map<String,String> disFavByPostId(@RequestBody Map<String,Integer> request,HttpSession session){
        Map<String,String> map=new HashMap<String,String>();
        Account account=(Account)session.getAttribute("user");
        for(Post post:account.getUserInfor().getFavoritePosting()){
            if(post.getId()==request.get("postId")){
                account.getUserInfor().getFavoritePosting().remove(post);
                postService.updateUserFav(account.getUserInfor());
                break;
            }
        }
        session.setAttribute("user",account);
        map.put("isSuccess","true");
        return map;
    }
    @RequestMapping("/thumbUpPost")
    @ResponseBody
    public Map<String,String> thumbUpPost(@RequestBody Map<String,Integer> request,HttpSession session){
        Map<String,String> map=new HashMap<String,String>();
        Account account=(Account)session.getAttribute("user");
        for(Post post:account.getUserInfor().getThumbUpPost()){
            if(post.getId()==request.get("postId")){
                account.getUserInfor().getFavoritePosting().remove(post);
                Post postd=postService.findEnetityById(request.get("postId"),Post.class);
                map.put("thumbUp","2");
                session.setAttribute("user",account);
                return map;
            }
        }
        Post post=postService.findEnetityById(request.get("postId"),Post.class);
        post.setThumpUpNum(post.getThumpUpNum()+1);
        postService.updateEntity(post);
        account.getUserInfor().getThumbUpPost().add(post);
        postService.updateUserFav(account.getUserInfor());
        map.put("thumbUp","1");
        session.setAttribute("user",account);
        return map;
    }

    @RequestMapping("/isAdminOrAuthor")
    @ResponseBody
    public Map<String,String> isAdminOrAuthor(HttpSession session,@RequestBody Map<String,String> request){
        Map<String,String> map=new HashMap<String,String>();
        Account account=(Account)session.getAttribute("user");
        if(account.getUsername().equals(request.get("username"))||account.getUsername().equals("admin"))
            map.put("yes","true");
        else
            map.put("yes","false");
        return map;
    }

    @RequestMapping("/deletePostByPostId")
    @ResponseBody
    public Map<String,String> deletePostByPostId(@RequestBody Map<String,Integer> request){
        Map<String,String> map=new HashMap<String,String>();
        Post yuan=postService.findEnetityById(request.get("postId"),Post.class);
        yuan.setDelete(true);
        postService.updateEntity(yuan);
        map.put("isSuccess","true");
        return map;
    }


    @RequestMapping("/addPost")
    @ResponseBody
    public Map<String,Integer> addPost(@RequestBody Map<String,String> request,HttpSession session){
        Post post=new Post();
        post.setDate(new Date());
        post.setDelete(false);
        post.setAccount((Account) session.getAttribute("user"));
        post.setCommentsnum(0);
        PostContent postContent=new PostContent();
        postContent.setContent(request.get("postContent"));
        postService.savePostContent(postContent);
        post.setPostContent(postContent);
        post.setTitle(request.get("title"));
        post.setThumpUpNum(0);
        post.setViewNum(0);
        post.setValid(false);
        post.setId((int)(System.currentTimeMillis()/1000-1510000000));
        Section sec=sectionService.getSectionById(Integer.parseInt(request.get("sectionId")));
        sec.setLastHandUp(post.getDate());
        sec.setPostNum(sec.getPostNum()+1);
        sectionService.updateEntity(sec);
        post.setSection(sec);
        postService.saveNewEntity(post);
        Map<String,Integer> map=new HashMap<>();
        map.put("id",post.getId());
        return map;
    }

    @RequestMapping("/search")
    @ResponseBody
    public List<Post> search(@RequestBody Map<String,Object> request){
        List<Post> postlist= postService.fuzzyQueryPostByTitle((String)request.get("content"));
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
    @RequestMapping("/recommond")
    @ResponseBody
    public List<Post> recommond(@RequestBody Map<String,Object> request){
        //参数是当前登录用户的userInfor 实体
        //Post post  = postService.recommendPost("");

        return null;
    }


}
