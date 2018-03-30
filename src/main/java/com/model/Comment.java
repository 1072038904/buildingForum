package com.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //评论内容
    private String content;
    //是否被阅读
    private boolean isRead;
    //是否对帖子回复
    private boolean isToPost;
    //回复排名
    private Integer replyRank;
    //评论的目标用户
    @ManyToOne
    @JoinColumn(name="targetAccount_id",referencedColumnName = "id")
    private Account targetAccount;
    @ManyToOne
    @JoinColumn(name="account_id",referencedColumnName = "id")
    private Account account;
    //评论对应的帖子
    @ManyToOne
    @JoinColumn(name="post_id",referencedColumnName = "id")
    private Post post;
    //发表评论的日期
    private Date date;
    public Account getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(Account targetAccount) {
        this.targetAccount = targetAccount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public boolean isToPost() {
        return isToPost;
    }

    public void setToPost(boolean toPost) {
        isToPost = toPost;
    }

    public Integer getReplyRank() {
        return replyRank;
    }

    public void setReplyRank(Integer replyRank) {
        this.replyRank = replyRank;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

}
