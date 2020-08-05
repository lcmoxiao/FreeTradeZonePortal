package com.ftzp.service.lc;

import com.ftzp.mapper.lc.UserMapper;
import com.ftzp.pojo.lc.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserMapper {

    UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> getUser(Integer uId) {
        return userMapper.getUser(uId);
    }

    @Override
    public User checkUser(User user) {
        return userMapper.checkUser(user);
    }

    @Override
    public void deleteUser(Integer uId) {
        userMapper.deleteUser(uId);
    }
}
