package com.ftzp.pojo.lc.user;

import java.io.Serializable;

public class Permission implements Serializable {

    Integer pId;
    String pName;
    String pSrc;

    public String getpSrc() {
        return pSrc;
    }

    public void setpSrc(String pSrc) {
        this.pSrc = pSrc;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }
}
