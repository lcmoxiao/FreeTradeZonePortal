package com.ftzp.pojo.lc.vote;

public class VoteOption {

    Integer oId;
    String odesc;
    Integer oCount;
    Integer ovId;

    public Integer getOvId() {
        return ovId;
    }

    public void setOvId(Integer ovId) {
        this.ovId = ovId;
    }

    public Integer getoId() {
        return oId;
    }

    public void setoId(Integer oId) {
        this.oId = oId;
    }

    public String getOdesc() {
        return odesc;
    }

    public void setOdesc(String odesc) {
        this.odesc = odesc;
    }

    public Integer getoCount() {
        return oCount;
    }

    public void setoCount(Integer oCount) {
        this.oCount = oCount;
    }
}
