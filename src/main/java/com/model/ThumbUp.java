package com.model;

import javafx.geometry.Pos;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;
@Entity
public class ThumbUp {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    //赞的用户
    @ManyToMany
    private Set<Account> accounts;
    @ManyToOne
    private Post post;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
