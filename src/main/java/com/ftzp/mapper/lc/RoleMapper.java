package com.ftzp.mapper.lc;

import com.ftzp.pojo.lc.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {

    List<Role> getRole(int rId);

    Role insertRole(@Param("rName") String rName, @Param("rPermission") int rPermission);
}