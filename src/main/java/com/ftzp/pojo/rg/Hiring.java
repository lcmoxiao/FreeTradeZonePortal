package com.ftzp.pojo.rg;

import java.util.Date;

public class Hiring {
    private Integer hiringId;
    private String hiringName;
    private String hiringEmail;
    private String hiringFile;
    private String hiringContent;
    private Date hiringTime;

    public Date getHiringTime() {
        return hiringTime;
    }

    public void setHiringTime(Date hiringTime) {
        this.hiringTime = hiringTime;
    }

    public Integer getHiringId() {
        return hiringId;
    }

    public void setHiringId(Integer hiringId) {
        this.hiringId = hiringId;
    }

    public String getHiringName() {
        return hiringName;
    }

    public void setHiringName(String hiringName) {
        this.hiringName = hiringName;
    }

    public String getHiringEmail() {
        return hiringEmail;
    }

    public void setHiringEmail(String hiringEmail) {
        this.hiringEmail = hiringEmail;
    }

    public String getHiringFile() {
        return hiringFile;
    }

    public void setHiringFile(String hiringFile) {
        this.hiringFile = hiringFile;
    }

    public String getHiringContent() {
        return hiringContent;
    }

    public void setHiringContent(String hiringContent) {
        this.hiringContent = hiringContent;
    }

    @Override
    public String toString() {
        return "hiring{" +
                "hiringId=" + hiringId +
                ", hiringName='" + hiringName + '\'' +
                ", hiringTime='" + hiringTime + '\'' +
                ", hiringEmail='" + hiringEmail + '\'' +
                ", hiringFile='" + hiringFile + '\'' +
                ", hiringContent='" + hiringContent + '\'' +
                '}';
    }
}
