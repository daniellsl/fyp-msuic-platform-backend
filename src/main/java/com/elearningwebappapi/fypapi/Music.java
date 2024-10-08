package com.elearningwebappapi.fypapi;

import javax.persistence.*;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "MUSIC")
public class Music {
    @Id
    @Column(name = "mid", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mid;

    @Column(name = "uid", length = 60, nullable = false)
    private long uid;

    @Column(name = "musicname", length = 30, nullable = false)
    private String musicname;

    @Column(name = "auth", length = 20, nullable = false)
    private String auth;

    @Column(name = "musicUrl", nullable = false)
    private String musicUrl;

    Music() {
        
    }

    Music(long uid, String musicname, String auth, String musicUrl) {
        this.uid = uid;
        this.musicname = musicname;
        this.auth = auth;
        this.musicUrl = musicUrl;
    }

    Music(long mid, long uid, String musicname, String auth, String musicUrl) {
        this.mid = mid;
        this.uid = uid;
        this.musicname = musicname;
        this.auth = auth;
        this.musicUrl = musicUrl;
    }

    public void setMid(long mid) {
        this.mid = mid;
    }

    public long getMid() {
        return mid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getUid() {
        return uid;
    }

    public void setMusicname(String musicname) {
        this.musicname = musicname;
    }

    public String getMusicname() {
        return musicname;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getAuth() {
        return auth;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    @Override
    public String toString() {
        return "Musiclist{" +
                "mid=" + mid +
                ", musicname='" + musicname + '\'' +
                ", auth='" + auth + '\'' +
                ", musicUrl='" + musicUrl + '\'' +
                "}";
    }
}