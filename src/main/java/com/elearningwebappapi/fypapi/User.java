package com.elearningwebappapi.fypapi;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class User {
    @Id
    @Column(name = "uid", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column(name = "username", length = 20, nullable = false, unique = true)
    private String username;

    @Column(name = "password", length = 20, nullable = false)
    private  String password;

    User() {

    }

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    User(long uid, String username, String password) {
        this.uid = uid;
        this.username = username;
        this.password = password;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getUid() {
        return uid;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Userlist{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}