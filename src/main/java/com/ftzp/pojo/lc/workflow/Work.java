package com.ftzp.pojo.lc.workflow;

import java.util.Date;

public class Work {

    Integer wId;
    Integer wfId;
    Integer ranking;
    String wFile;
    String wdesc;
    Integer wLength;
    Integer uId;
    Date wLastDoTime;
    Date wPostTime;
    String wContent;
    Integer wUnCheckedContentId;

    public Integer getwUnCheckedContentId() {
        return wUnCheckedContentId;
    }

    public void setwUnCheckedContentId(Integer wUnCheckedContentId) {
        this.wUnCheckedContentId = wUnCheckedContentId;
    }

    public String getwContent() {
        return wContent;
    }

    public void setwContent(String wContent) {
        this.wContent = wContent;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getwLength() {
        return wLength;
    }

    public void setwLength(Integer wLength) {
        this.wLength = wLength;
    }

    public String getWdesc() {
        return wdesc;
    }

    public void setWdesc(String wdesc) {
        this.wdesc = wdesc;
    }

    @Override
    public String toString() {
        return "Work{" +
                "wId=" + wId +
                ", wfId=" + wfId +
                ", ranking=" + ranking +
                ", wFile='" + wFile + '\'' +
                '}';
    }

    public String getwFile() {
        return wFile;
    }

    public void setwFile(String wFile) {
        this.wFile = wFile;
    }

    public int getwId() {
        return wId;
    }

    public void setwId(Integer wId) {
        this.wId = wId;
    }

    public int getWfId() {
        return wfId;
    }

    public void setWfId(Integer wfId) {
        this.wfId = wfId;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }


    public Date getwLastDoTime() {
        return wLastDoTime;
    }

    public void setwLastDoTime(Date wLastDoTime) {
        this.wLastDoTime = wLastDoTime;
    }

    public Date getwPostTime() {
        return wPostTime;
    }

    public void setwPostTime(Date wPostTime) {
        this.wPostTime = wPostTime;
    }
}
