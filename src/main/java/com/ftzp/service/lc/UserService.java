package com.ftzp.service.lc;

import com.ftzp.Sha256Util;
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
        user.setuPass(Sha256Util.getSHA256(user.getuPass()));
        return userMapper.checkUser(user);
    }

    @Override
    public void insertUser(User user) {
        user.setuPass(Sha256Util.getSHA256(user.getuPass()));
        userMapper.insertUser(user);
    }

    @Override
    public void updateUser(User user) {
        user.setuPass(Sha256Util.getSHA256(user.getuPass()));
        userMapper.updateUser(user);
    }

    @Override
    public void deleteUser(Integer uId) {
        userMapper.deleteUser(uId);
    }


}
