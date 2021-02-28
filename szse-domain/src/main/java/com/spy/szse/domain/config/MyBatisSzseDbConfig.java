package com.spy.szse.domain.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * @Author lei.zhao_ext
 * @Date 2021/2/26
 */
@Configuration
@MapperScan(basePackages = {MyBatisSzseDbConfig.BASE_PACKAGES},
        sqlSessionTemplateRef = "sqlSessionTemplate")
public class MyBatisSzseDbConfig {

    public static final String BASE_PACKAGES = "com.spy.szse.svc.mapper.szse";

    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("szseDataSource") DataSource dataSource) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory(dataSource));
    }

    private SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResource("classpath:mapper/mybatis/szse/*.xml"));
        bean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return bean.getObject();
    }
}
