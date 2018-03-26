package com.model;


import javax.persistence.*;
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(unique=true)
    private  Integer account;
    private String password;
    private Integer jurisdiction;
    public Integer getJurisdiction() {
        return jurisdiction;
    }
    public void setJurisdiction(Integer jurisdiction) {
        this.jurisdiction = jurisdiction;
    }
    public Integer getAccount() {
        return account;
    }
    public void setAccount(Integer account) {
        this.account = account;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }


}
