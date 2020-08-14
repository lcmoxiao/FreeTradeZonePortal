package com.ftzp.pojo.lc.user;

public class Role {

    Integer rId;
    String rName;
    //数据库存储为char格式8位，包括128位，用于减少损耗以及权限行。上限暂定为128个功能权限。
    String rPermission;

    public int getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getrPermission() {
        return rPermission;
    }

    public void setrPermission(String rPermission) {
        this.rPermission = rPermission;
    }
}
