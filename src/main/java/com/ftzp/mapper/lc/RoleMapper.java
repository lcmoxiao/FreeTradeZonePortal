package com.ftzp.mapper.lc;

import com.ftzp.pojo.lc.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {

    List<Role> getRole(Integer rId);

    void insertRole(Role role);

    void updateRole(Role role);

    void deleteRole(Integer rId);
}