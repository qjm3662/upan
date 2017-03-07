package cn.qjm253.entity;

import javax.persistence.*;

/**
 * Created by qjm3662 on 2017/3/2 0002.
 */
@Entity
public class FeedBack {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)     //主键自增
    private int fb_id;
    private String text;
    private String username;
    private String phoneNumber;

    public FeedBack() {
    }

    public int getFb_id() {
        return fb_id;
    }

    public void setFb_id(int fb_id) {
        this.fb_id = fb_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
