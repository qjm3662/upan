package cn.qjm253.entity;

import javax.persistence.Embeddable;

/**
 * Created by Administrator on 2017/3/9/009.
 */
public class SimpleUserInfo {
    private String username;
    private String nickname;
    private String signature;
    private String avatar;
    private int sex;

    public SimpleUserInfo() {
    }

    public SimpleUserInfo(User user){
        if(user == null){       //如果传入的为null
            System.out.println("The param user is null!!");
            return;
        }
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.signature = user.getSignature();
        this.avatar = user.getAvatar();
        this.sex = user.getSex();
    }
    public SimpleUserInfo(String username, String nickname, String signature, String avatar, int sex) {
        this.username = username;
        this.nickname = nickname;
        this.signature = signature;
        this.avatar = avatar;
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
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
}
