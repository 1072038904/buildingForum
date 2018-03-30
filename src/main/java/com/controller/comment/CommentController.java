package com.controller.comment;

import com.model.Account;
import com.model.Comment;
import com.model.Post;
import com.service.CommentManage.CommentService;
import com.service.PostManage.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    @Qualifier("commentServiceImple")
    private CommentService commentService;
    @Autowired
    @Qualifier("postServiceImple")
    private PostService postService;

    @RequestMapping("/addComment")
    @ResponseBody
    public Map<String,String> addComment(@RequestBody Map<String,String> request, HttpSession session ){
        Post post=new Post();
        post.setId(Integer.parseInt(request.get("postId")));
        Account target=new Account();
        target.setId(Integer.parseInt(request.get("targetId")));
        Comment comment=new Comment();
        comment.setTargetAccount(target);
        comment.setPost(post);
        comment.setAccount((Account)session.getAttribute("user"));
        comment.setContent(request.get("neirong"));
        comment.setDate(new Date());
        comment.setRead(false);
        comment.setReplyRank(Integer.parseInt(request.get("replyRank")));
        if(request.get("isToPost").equals("0"))
            comment.setToPost(false);
        else {
            comment.setToPost(true);
            Post pos=postService.findEnetityById(Integer.parseInt(request.get("postId")),Post.class);
            pos.setCommentsnum(pos.getCommentsnum()+1);
            postService.updateEntity(pos);
        }
        commentService.saveNewEntity(comment);
        Map<String,String> map=new HashMap<String,String>();
        map.put("isSuccess","true");
        return map;
    }
}