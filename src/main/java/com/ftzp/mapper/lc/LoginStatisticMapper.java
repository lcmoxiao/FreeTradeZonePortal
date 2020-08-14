package com.ftzp.mapper.lc;

import com.ftzp.pojo.lc.LoginStatistic;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface LoginStatisticMapper {

    List<LoginStatistic> getLoginStatistic(@Param("start") Date start, @Param("end") Date end);

    void insertLoginStatistic(LoginStatistic loginStatistic);

    List<LoginStatistic> getLoginStatisticToDayNum(@Param("dateFormat") String dateFormat, @Param("start") Date start, @Param("end") Date end);
}