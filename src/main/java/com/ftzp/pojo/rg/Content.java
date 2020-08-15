package com.ftzp.pojo.rg;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Content {
    private Integer contentId;
    private String contentName;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    private int sumCon;
    private int flagCheck;

    public int getFlagCheck() {
        return flagCheck;
    }

    public void setFlagCheck(int flagCheck) {
        this.flagCheck = flagCheck;
    }

    public int getSumCon() {
        return sumCon;
    }

    public void setSumCon(int sumCon) {
        this.sumCon = sumCon;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    private Integer columnId1;
    private Integer columnId2;
    private Integer columnId3;

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getColumnId1() {
        return columnId1;
    }

    public void setColumnId1(Integer columnId1) {
        this.columnId1 = columnId1;
    }

    public Integer getColumnId2() {
        return columnId2;
    }

    public void setColumnId2(Integer columnId2) {
        this.columnId2 = columnId2;
    }

    public Integer getColumnId3() {
        return columnId3;
    }

    public void setColumnId3(Integer columnId3) {
        this.columnId3 = columnId3;
    }

    @Override
    public String toString() {
        return "Content{" +
                "contentId=" + contentId +
                ", contentName='" + contentName + '\'' +
                ", content='" + content + '\'' +
                ", sumCon='" + sumCon + '\'' +
                ", flagCheck='" + flagCheck + '\'' +
                ", birth='" + birth + '\'' +
                ", columnId1=" + columnId1 +
                ", columnId2=" + columnId2 +
                ", columnId3=" + columnId3 +
                '}';
    }
}
