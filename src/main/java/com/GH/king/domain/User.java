package com.GH.king.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by alex on 2016/1/22.
 */
@Entity
@Table(name = "Users")
public class User {
    @Column(name = "name")
    private String userName;
    @Column(name = "password")
    private String userPsd;
    @Id
    @Column(name = "Id")
    private String userId;
    @Column(name = "email")
    private String email;
    @Column(name = "role")
    private String role;
    @Column(name = "profile")
    private String headPic;


    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPsd() {
        return userPsd;
    }

    public void setUserPsd(String userPsd) {
        this.userPsd = userPsd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }
}
