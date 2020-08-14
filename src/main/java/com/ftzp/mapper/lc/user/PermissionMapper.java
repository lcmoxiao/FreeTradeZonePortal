package com.ftzp.mapper.lc.user;

import com.ftzp.pojo.lc.user.Permission;

import java.util.ArrayList;
import java.util.List;

public interface PermissionMapper {

    List<Permission> getPermission(Integer pId);

    void insertPermission(Permission permission);

    void deletePermission(Integer pId);

    void updatePermission(Permission permission);

    ArrayList<Permission> getPermissionByList(List<Integer> permissionList);
}
