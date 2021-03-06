package com.ftzp.mapper.lc.user;

import com.ftzp.pojo.lc.user.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    List<User> getUser(@Param("uId") Integer uId);

    User checkUser(User user);

    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(@Param("uId") Integer uId);
}