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
    //电话
    private Integer telephone;
    //电子邮件
    private String email;
    //余额
    private double balance;
    //信用等级
    private double credit;
    //账号等级
    private Integer level;

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

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
    @ManyToMany
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name="section_id",referencedColumnName = "id")
    private Set<Section> favoriteSection;
    @ManyToMany
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name="posting_id",referencedColumnName = "id")
    private Set<Post> favoritePosting;
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

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
