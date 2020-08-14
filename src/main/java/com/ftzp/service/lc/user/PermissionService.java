package com.ftzp.service.lc.user;

import com.ftzp.mapper.lc.user.PermissionMapper;
import com.ftzp.pojo.lc.user.Permission;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.ftzp.PermissionParseUtil.getPIdList;


@Service("permissionService")
public class PermissionService {

    @Resource
    PermissionMapper permissionMapper;

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
    public ArrayList<Permission> getPermission(String rPermission) {
        return permissionMapper.getPermissionByList(getPIdList(rPermission));
    }
}
