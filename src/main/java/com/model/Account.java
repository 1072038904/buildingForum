package com.model;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(unique=true)
    private  Integer account;
    private String username;
    private String password;
    private Integer jurisdiction;
    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "userInfor_id", referencedColumnName = "id")
    private UserInfor userInfor;
    @OneToMany(fetch=FetchType.EAGER,mappedBy = "account")
    private Set<Post> post;
    @OneToMany(fetch=FetchType.EAGER,mappedBy = "account")
    private Set<Comment> comment;
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

    public UserInfor getUserInfor() {
        return userInfor;
    }

    public void setUserInfor(UserInfor userInfor) {
        this.userInfor = userInfor;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Post> getPost() {
        return post;
    }

    public void setPost(Set<Post> post) {
        this.post = post;
    }

    public Set<Comment> getComment() {
        return comment;
    }

    public void setComment(Set<Comment> comment) {
        this.comment = comment;
    }
}
