package com.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //帖子对应的评论
    @OneToMany(mappedBy = "post")
    private Set<Comment> comments=new HashSet<>();
    //标题
    private String title;
    //帖子内容
    @OneToOne
    @JoinColumn(name="postContent_id",referencedColumnName = "id")
    private PostContent postContent;
    //帖子属于的分类
    @ManyToOne
    @JoinColumn(name="section_id",referencedColumnName = "id")
    private Section section;
    //贴子是否合法
    private boolean isValid;
    //贴子查看数量
    private Integer viewNum;
    //评论数量
    private Integer commentsnum;
    //日期
    private Date postReleaseDate;
    //贴子是否被删除
    private boolean isDelete;
    //赞的数量
    private Integer thumpUpNum;
    //赞
    @OneToMany
    @JoinColumn(name="thumbUp_id",referencedColumnName = "id")
    private Set<ThumbUp> thumbUpSet =new HashSet<>();
    //帖子上传的附件
    @OneToMany
    @JoinColumn(name="annex_id")
    private Set<Annex> annexes = new HashSet<>();
    //发帖子的人
    @ManyToOne
    @JoinColumn(name="account_id",referencedColumnName = "id")
    private Account account;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PostContent getPostContent() {
        return postContent;
    }

    public void setPostContent(PostContent postContent) {
        this.postContent = postContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public Integer getViewNum() {
        return viewNum;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

    public Integer getCommentsnum() {
        return commentsnum;
    }

    public void setCommentsnum(Integer commentsnum) {
        this.commentsnum = commentsnum;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Date getPostReleaseDate() {
        return postReleaseDate;
    }

    public void setPostReleaseDate(Date postReleaseDate) {
        this.postReleaseDate = postReleaseDate;
    }

    public Integer getThumpUpNum() {
        return thumpUpNum;
    }

    public void setThumpUpNum(Integer thumpUpNum) {
        this.thumpUpNum = thumpUpNum;
    }

    public Set<ThumbUp> getThumbUpSet() {
        return thumbUpSet;
    }

    public void setThumbUpSet(Set<ThumbUp> thumbUpSet) {
        this.thumbUpSet = thumbUpSet;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
