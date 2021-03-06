package com.ftzp.service.lc.user;

import com.ftzp.mapper.lc.user.RoleMapper;
import com.ftzp.pojo.lc.user.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/*
权限生成流程：
传入权限数组，generatePStr(int[] permissions)生成128位权限字符串。
再用BinStrToStr(String bstr)将其转化成8位的存储字符串
以便存储到数据库

权限判断流程：
先从数据库中取得role对应的权限字符串，
先用StrToBinStr将存储的8位字符串扩展为128位的权限字符串，
再使用checkPermission(String rPermission, Integer functionNeed)判断权限
 */


@Service("roleService")
public class RoleService implements RoleMapper {

    @Resource
    RoleMapper roleMapper;

    @Override
    public List<Role> getRole(Integer rId) {
        return roleMapper.getRole(rId);
    }

    @Override
    public void insertRole(Role role) {
        roleMapper.insertRole(role);
    }

    @Override
    public void updateRole(Role role) {
        roleMapper.updateRole(role);
    }

    @Override
    public void deleteRole(Integer rId) {
        roleMapper.deleteRole(rId);
    }

}
