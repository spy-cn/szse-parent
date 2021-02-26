package com.spy.szse.domain.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 数据源配置
 *
 * @Author lei.zhao_ext
 * @Date 2021/2/26
 */
//@Configuration
public class DataSourceConfig {
  /*  @Bean("szseDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.szse")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }*/
}
