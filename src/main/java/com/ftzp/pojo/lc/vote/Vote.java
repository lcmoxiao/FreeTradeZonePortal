package com.ftzp.pojo.lc.vote;

import java.util.Date;

public class Vote {

    Integer vId;
    String vdesc;
    String vName;
    Date vDeadTime;
    Boolean vActive;
    Date vPublishTime;

    public Date getvPublishTime() {
        return vPublishTime;
    }

    public void setvPublishTime(Date vPublishTime) {
        this.vPublishTime = vPublishTime;
    }

    public Integer getvId() {
        return vId;
    }

    public void setvId(Integer vId) {
        this.vId = vId;
    }

    public String getVdesc() {
        return vdesc;
    }

    public void setVdesc(String vdesc) {
        this.vdesc = vdesc;
    }

    public String getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName = vName;
    }

    public Date getvDeadTime() {
        return vDeadTime;
    }

    public void setvDeadTime(Date vDeadTime) {
        this.vDeadTime = vDeadTime;
    }

    public Boolean getvActive() {
        return vActive;
    }

    public void setvActive(Boolean vActive) {
        this.vActive = vActive;
    }
}
