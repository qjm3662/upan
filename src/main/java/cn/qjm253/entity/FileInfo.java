package cn.qjm253.entity;

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
    private String fileSize;
    @Column(length = 6)
    private String identifyCode;
    private long createTime;
    private long updateTime;
    private int downloadCount;
    private boolean isPublic;
    private String saveName;        //包含了UUID码的文件名
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "uid")
    private transient User owner;

    @Transient
    private int code;
    @Transient
    private String codeMSG;

    public FileInfo() {
    }

    public FileInfo(String fileName, String fileSize, String identifyCode, long createTime, long updateTime, int downloadCount, boolean isPublic, String saveName) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.identifyCode = identifyCode;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.downloadCount = downloadCount;
        this.isPublic = isPublic;
        this.saveName = saveName;
    }


    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
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

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
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
}
