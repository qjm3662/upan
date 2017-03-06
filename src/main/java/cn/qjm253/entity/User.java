package cn.qjm253.entity;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by qjm3662 on 2017/3/2 0002.
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)     //主键自增
    private int uid;
    private String username;
    private String password;
    private String nickname;
    private String avatar;
    private String signature;
    private int sex;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "uid")
    private Set<FileInfo> shares = new HashSet<FileInfo>();
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "fi_id")
    private FollowInfo followInfo;


    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public User(String username, String nickname, String avatar, String signature, int sex) {
        this.username = username;
        this.nickname = nickname;
        this.avatar = avatar;
        this.signature = signature;
        this.sex = sex;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public FollowInfo getFollowInfo() {
        return followInfo;
    }

    public void setFollowInfo(FollowInfo followInfo) {
        this.followInfo = followInfo;
    }

    public Set<FileInfo> getShares() {
        return shares;
    }

    public void setShares(Set<FileInfo> shares) {
        this.shares = shares;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", signature='" + signature + '\'' +
                ", sex=" + sex +
                ", shares=" + shares +
                ", followInfo=" + followInfo +
                '}';
    }
}
