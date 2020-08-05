package com.ftzp.service.lc;

import com.ftzp.mapper.lc.PermissionMapper;
import com.ftzp.pojo.lc.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class PermissionService implements PermissionMapper {

    PermissionMapper permissionMapper;


    @Autowired
    public void setPermissionMapper(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    @Override
    public Permission getPermission(int pId) {
        return getPermission(pId);
    }

    @Override
    public Permission insertPerssion(String pName) {
        return permissionMapper.insertPerssion(pName);
    }


}
