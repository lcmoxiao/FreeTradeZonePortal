package com.ftzp.mapper.lc;

import com.ftzp.pojo.lc.Permission;

import java.util.List;

public interface PermissionMapper {

    List<Permission> getPermission(Integer pId);

    void insertPermission(Permission permission);

    void deletePermission(Integer pId);

    void updatePermission(Permission permission);

    List<Permission> getPermissionByList(List<Integer> permissionList);
}
