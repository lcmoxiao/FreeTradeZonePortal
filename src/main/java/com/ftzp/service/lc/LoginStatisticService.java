package com.ftzp.service.lc;

import com.ftzp.mapper.lc.LoginStatisticMapper;
import com.ftzp.pojo.lc.LoginStatistic;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("loginStatisticService")
@Scope(ConfigurableListableBeanFactory.SCOPE_SINGLETON)
public class LoginStatisticService {

    @Resource
    LoginStatisticMapper loginStatisticMapper;

    public List<LoginStatistic> getStatistic(Date start, Date end) {
        return loginStatisticMapper.getLoginStatistic(start, end);
    }

    public List<LoginStatistic> getLoginStatisticToDayNum(String dateFormat, Date start, Date end) {
        return loginStatisticMapper.getLoginStatisticToDayNum(dateFormat, start, end);
    }

    public void insertStatistic(LoginStatistic loginStatistic) {
        loginStatisticMapper.insertLoginStatistic(loginStatistic);
    }

}
