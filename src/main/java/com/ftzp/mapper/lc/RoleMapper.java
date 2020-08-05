package com.ftzp.mapper.lc;

import com.ftzp.pojo.lc.Role;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {

    Role getRole(int rId);

    Role insertRole(@Param("rName") String rName, @Param("rPermission") int rPermission);
}