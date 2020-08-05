package com.ftzp.pojo.lc;

public class Work {

    Integer wId;
    Integer wfId;
    Integer ranking;
    String wFile;
    String wdesc;
    Integer wLength;
    Integer uId;

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


}
