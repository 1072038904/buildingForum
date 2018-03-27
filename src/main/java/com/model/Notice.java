package com.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Notice {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    //通知标题
    private String noticeTitle;
    //通知类型（会议、通告、公告）
    private String noticeType;
    //创建时间
    private Date noticeReleaseDate;
    //通知内容
    private String noticeContent;
    //更新时间
    private Date noticeUpdateDate;
    //附件
    private Set<String> noticeUploadPaths =new HashSet<>();
    //查看状态
    private  boolean isRead;
    @ManyToOne
    private Account account;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public Date getNoticeReleaseDate() {
        return noticeReleaseDate;
    }

    public void setNoticeReleaseDate(Date noticeReleaseDate) {
        this.noticeReleaseDate = noticeReleaseDate;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public Date getNoticeUpdateDate() {
        return noticeUpdateDate;
    }

    public void setNoticeUpdateDate(Date noticeUpdateDate) {
        this.noticeUpdateDate = noticeUpdateDate;
    }

    public Set<String> getNoticeUploadPaths() {
        return noticeUploadPaths;
    }

    public void setNoticeUploadPaths(Set<String> noticeUploadPaths) {
        this.noticeUploadPaths = noticeUploadPaths;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
