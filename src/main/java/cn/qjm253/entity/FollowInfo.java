package cn.qjm253.entity;

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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fi_id")
    private Set<User> following;
    @Transient
    private int code;
    @Transient
    private String codeMSG;

    public FollowInfo() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCodeMSG() {
        return codeMSG;
    }

    public void setCodeMSG(String codeMSG) {
        this.codeMSG = codeMSG;
    }

    public int getFi_id() {
        return fi_id;
    }

    public void setFi_id(int fi_id) {
        this.fi_id = fi_id;
    }

    public Set<User> getFollowing() {
        return following;
    }

    public void setFollowing(Set<User> following) {
        this.following = following;
    }
}
