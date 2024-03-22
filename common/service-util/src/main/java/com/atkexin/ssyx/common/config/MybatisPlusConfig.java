package com.atkexin.ssyx.common.config;

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
//@ConditionalOnProperty(name = "mybatis-plus.enabled", havingValue = "true", matchIfMissing = true)
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
