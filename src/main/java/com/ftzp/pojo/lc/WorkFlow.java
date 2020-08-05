package com.ftzp.pojo.lc;

import java.util.List;

public class WorkFlow {
    Integer wfId;   //WorkFlow 的唯一ID
    String wfName;  //WorkFlow的Name
    Integer wfLength;
    String wfdesc;

    //联机查询
    List<WorkStep> wws;

    public Integer getWfLength() {
        return wfLength;
    }

    public void setWfLength(Integer wfLength) {
        this.wfLength = wfLength;
    }

    public String getWfdesc() {
        return wfdesc;
    }

    public void setWfdesc(String wfdesc) {
        this.wfdesc = wfdesc;
    }

    public List<WorkStep> getWws() {
        return wws;
    }

    public void setWws(List<WorkStep> wws) {
        this.wws = wws;
    }

    public int getWfId() {
        return wfId;
    }

    public void setWfId(Integer wfId) {
        this.wfId = wfId;
    }

    public String getWfName() {
        return wfName;
    }

    public void setWfName(String wfName) {
        this.wfName = wfName;
    }


}
