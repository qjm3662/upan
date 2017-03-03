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
    @Transient
    private int code;
    @Transient
    private String codeMSG;

    public FeedBack() {
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


}
