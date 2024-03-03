package com.atkexin.ssyx.common.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * MybatisPlus配置类
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.atkexin.ssyx.*.mapper")
public class MybatisPlusConfig {

    /**
     * mp插件
     */
        @Autowired
        private DataSource dataSource;

        @Bean
        public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean() {
            MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
            mybatisPlus.setDataSource(dataSource);
            return mybatisPlus;
        }

}
