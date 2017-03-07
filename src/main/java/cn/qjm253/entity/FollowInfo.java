package cn.qjm253.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by qjm3662 on 2017/3/3 0003.
 */
@Entity
public class FollowInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)     //主键自增
    private int fi_id;
    private String myselfName;
    private String otherName;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "uid")
    @JsonIgnore
    private User other;
    private long createTime;

    @Transient
    private int sex;
    @Transient
    private String avatar;
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "fi_id")
//    private Set<User> following;

    public FollowInfo() {
    }

    public String getMyselfName() {
        return myselfName;
    }

    public void setMyselfName(String myselfName) {
        this.myselfName = myselfName;
    }

    public User getOther() {
        return other;
    }

    public void setOther(User other) {
        this.other = other;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getFi_id() {
        return fi_id;
    }

    public void setFi_id(int fi_id) {
        this.fi_id = fi_id;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "FollowInfo{" +
                "fi_id=" + fi_id +
                ", myselfName='" + myselfName + '\'' +
                ", otherName='" + otherName + '\'' +
                ", other=" + other +
                ", createTime=" + createTime +
                '}';
    }

    //    public Set<User> getFollowing() {
//        return following;
//    }
//
//    public void setFollowing(Set<User> following) {
//        this.following = following;
//    }
}
