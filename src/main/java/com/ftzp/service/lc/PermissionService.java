package com.ftzp.service.lc;

import com.ftzp.mapper.lc.PermissionMapper;
import com.ftzp.pojo.lc.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ftzp.PermissionParseUtil.getPIdList;


@Service
public class PermissionService {


    PermissionMapper permissionMapper;

    @Autowired
    public void setPermissionMapper(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }


    public List<Permission> getPermission(Integer pId) {
        return permissionMapper.getPermission(pId);
    }


    public void insertPermission(Permission permission) {
        permissionMapper.insertPermission(permission);
    }


    public void deletePermission(Integer pId) {
        permissionMapper.deletePermission(pId);
    }


    public void updatePermission(Permission permission) {
        permissionMapper.updatePermission(permission);
    }

    //通过权限字串获取权限列表
    public List<Permission> getPermission(String rPermission) {
        return permissionMapper.getPermissionByList(getPIdList(rPermission));
    }
}
