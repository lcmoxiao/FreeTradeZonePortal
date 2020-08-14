package com.ftzp.mapper.lc.user;

import com.ftzp.pojo.lc.user.Role;

import java.util.List;

public interface RoleMapper {

    List<Role> getRole(Integer rId);

    void insertRole(Role role);

    void updateRole(Role role);

    void deleteRole(Integer rId);
}