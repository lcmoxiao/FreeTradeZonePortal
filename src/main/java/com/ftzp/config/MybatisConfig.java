package com.ftzp.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class MybatisConfig {

    @Bean("dataSource")
    BasicDataSource initBasicDataSource() {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        bds.setUrl("jdbc:mysql://localhost:3306/db_ftz?useSSL=true&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8");
        bds.setUsername("root");
        bds.setPassword("123");
        bds.setInitialSize(5);
        bds.setMaxIdle(10);
        bds.setMaxTotal(20);
        bds.setMinIdle(1);
        return bds;
    }

    @Bean("sqlSessionFactoryBean")
    SqlSessionFactoryBean initSqlSessionFactoryBean(BasicDataSource dataSource) {
        SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
        ssfb.setDataSource(dataSource);
        return ssfb;
    }

    @Bean("dataSourceTransactionManager")
    DataSourceTransactionManager initDataSourceTransactionManager(BasicDataSource dataSource) {
        DataSourceTransactionManager mstm = new DataSourceTransactionManager();
        mstm.setDataSource(dataSource);
        return mstm;
    }

}
