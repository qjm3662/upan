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
    @Transient      //改属性不在数据库中出现，只是为了返回给用户的时候加入json串中
    private SimpleUserInfo owner;   //用来存储用户信息

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private User owner_user;

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

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public SimpleUserInfo getOwner() {
        return owner;
    }

    public void setOwner(SimpleUserInfo owner) {
        this.owner = owner;
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

    public User getOwner_user() {
        return owner_user;
    }

    public void setOwner_user(User owner_user) {
        this.owner_user = owner_user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FileInfo)) return false;

        FileInfo fileInfo = (FileInfo) o;

        if (getFid() != fileInfo.getFid()) return false;
        if (Double.compare(fileInfo.getFileSize(), getFileSize()) != 0) return false;
        if (getCreateTime() != fileInfo.getCreateTime()) return false;
        if (getUpdateTime() != fileInfo.getUpdateTime()) return false;
        if (getDownloadCount() != fileInfo.getDownloadCount()) return false;
        if (isPublic() != fileInfo.isPublic()) return false;
        if (getFileName() != null ? !getFileName().equals(fileInfo.getFileName()) : fileInfo.getFileName() != null)
            return false;
        if (getIdentifyCode() != null ? !getIdentifyCode().equals(fileInfo.getIdentifyCode()) : fileInfo.getIdentifyCode() != null)
            return false;
        if (getSaveName() != null ? !getSaveName().equals(fileInfo.getSaveName()) : fileInfo.getSaveName() != null)
            return false;
        if (getOwner() != null ? !getOwner().equals(fileInfo.getOwner()) : fileInfo.getOwner() != null) return false;
        return getOwner_user() != null ? getOwner_user().equals(fileInfo.getOwner_user()) : fileInfo.getOwner_user() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getFid();
        result = 31 * result + (getFileName() != null ? getFileName().hashCode() : 0);
        temp = Double.doubleToLongBits(getFileSize());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getIdentifyCode() != null ? getIdentifyCode().hashCode() : 0);
        result = 31 * result + (int) (getCreateTime() ^ (getCreateTime() >>> 32));
        result = 31 * result + (int) (getUpdateTime() ^ (getUpdateTime() >>> 32));
        result = 31 * result + getDownloadCount();
        result = 31 * result + (isPublic() ? 1 : 0);
        result = 31 * result + (getSaveName() != null ? getSaveName().hashCode() : 0);
        result = 31 * result + (getOwner() != null ? getOwner().hashCode() : 0);
        result = 31 * result + (getOwner_user() != null ? getOwner_user().hashCode() : 0);
        return result;
    }
}
