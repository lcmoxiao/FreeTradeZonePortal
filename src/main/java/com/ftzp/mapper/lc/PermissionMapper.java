package com.ftzp.mapper.lc;

import com.ftzp.pojo.lc.Permission;

public interface PermissionMapper {

    Permission getPermission(int pId);

    Permission insertPerssion(String pName);
}