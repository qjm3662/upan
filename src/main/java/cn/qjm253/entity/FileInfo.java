package cn.qjm253.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by qjm3662 on 2017/3/2 0002.
 */
@Entity
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)     //主键自增
    private int fid;
    private String fileName;
    private double fileSize;
    @Column(length = 6)
    private String identifyCode;
    private long createTime;
    @JsonIgnore
    private long updateTime;
    private int downloadCount;
    private boolean isPublic;
    @JsonIgnore
    private String saveName;        //包含了UUID码的文件名
    private String owner;

    public FileInfo() {
    }

    public FileInfo(String fileName, double fileSize, String identifyCode, long createTime, long updateTime, int downloadCount, boolean isPublic, String saveName) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.identifyCode = identifyCode;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.downloadCount = downloadCount;
        this.isPublic = isPublic;
        this.saveName = saveName;
    }

    public FileInfo(int fid, String fileName, double fileSize, String identifyCode, long createTime, long updateTime) {
        this.fid = fid;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.identifyCode = identifyCode;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public double getFileSize() {
        return fileSize;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }

    public String getIdentifyCode() {
        return identifyCode;
    }

    public void setIdentifyCode(String identifyCode) {
        this.identifyCode = identifyCode;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setSaveName(String saveName) {

        this.saveName = saveName;
    }
}
