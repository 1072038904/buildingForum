package com.model;

import javax.persistence.*;
import com.model.Post;
import javafx.geometry.Pos;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Section {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    //帖子分类名
    private String sectionName;
    //板块帖子最后上交时间
    private String lastHandUp;
    //板块阅读人数
    private Integer readNum;
    //帖子的数量
    private Integer postNum;
    @ManyToOne
    @JoinColumn(name="board_id",referencedColumnName = "id")
    private Board board;
    @OneToMany
    private Set<Post> postSet=new HashSet<>();
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getLastHandUp() {
        return lastHandUp;
    }

    public Integer getReadNum() {
        return readNum;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    public Integer getPostNum() {
        return postNum;
    }

    public void setPostNum(Integer postNum) {
        this.postNum = postNum;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Set<Post> getPostSet() {
        return postSet;
    }

    public void setPostSet(Set<Post> postSet) {
        this.postSet = postSet;
    }

    public void setLastHandUp(String lastHandUp) {
        this.lastHandUp = lastHandUp;
    }
}
