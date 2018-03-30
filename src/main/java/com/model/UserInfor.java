package com.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Entity
public class UserInfor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //性别
    private String sex;
    //业余爱好
    private String hobby;
    private int postNum;
    //电话
    private String telephone;
    //电子邮件
    private String email;
    //余额
    private double balance;
    //信用等级
    private double credit;
    //账号等级
    private Integer level;

    @ManyToMany
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name="section_id",referencedColumnName = "id")
    private Set<Section> favoriteSection;

    @ManyToMany
    @JoinTable(name="post_thumbUp")
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name="posting_id",referencedColumnName = "id")
    private Set<Post> thumbUpPost;

    @ManyToMany
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name="posting_id",referencedColumnName = "id")
    private Set<Post> favoritePosting;
    @OneToOne
    private Account account;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Set<Post> getThumbUpPost() {
        return thumbUpPost;
    }

    public void setThumbUpPost(Set<Post> thumbUpPost) {
        this.thumbUpPost = thumbUpPost;
    }

    public Set<Section> getFavoriteSection() {
        return favoriteSection;
    }

    public void setFavoriteSection(Set<Section> favoriteSection) {
        this.favoriteSection = favoriteSection;
    }

    public Set<Post> getFavoritePosting() {
        return favoritePosting;
    }

    public void setFavoritePosting(Set<Post> favoritePosting) {
        this.favoritePosting = favoritePosting;
    }

    public int getPostNum() {
        return postNum;
    }

    public void setPostNum(int postNum) {
        this.postNum = postNum;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
