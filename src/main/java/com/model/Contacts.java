package com.model;

import javax.persistence.*;
import javax.xml.ws.Service;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Contacts {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    //会员名
    private String vipName;
    //姓名
    private String contactName;
    //部门
    @OneToMany
    @JoinColumn(name="department_id",referencedColumnName = "id")
    private Set<Department> departmentSet = new HashSet<>();
    //联系方式
    private String telePhone;
    //邮箱
    private String email;
    //备注
    private String noteName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVipName() {
        return vipName;
    }

    public void setVipName(String vipName) {
        this.vipName = vipName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public Set<Department> getDepartmentSet() {
        return departmentSet;
    }

    public void setDepartmentSet(Set<Department> departmentSet) {
        this.departmentSet = departmentSet;
    }

    public String getTelePhone() {
        return telePhone;
    }

    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone;
    }
}
