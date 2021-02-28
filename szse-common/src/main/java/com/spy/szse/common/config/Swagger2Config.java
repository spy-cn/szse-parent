package com.spy.szse.common.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: spy
 * @Date: 2021/2/28 11:00
 */
@EnableKnife4j
@EnableSwagger2
@Configuration
public class Swagger2Config {
    public Swagger2Config() {
        System.err.println("====Controller");
    }

    @Bean
    public Docket apiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("adminApi")
                .apiInfo(apiInfo())
                .select()
                //只显示controller路径下的页面
                .apis(RequestHandlerSelectors.basePackage("com.spy.szse.svc.controller"))
                .paths(Predicates.and(PathSelectors.regex("/controller/.*")))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API接口文档")
                .description("本文档描述了接口的定义文档")
                .version("1.0")
                .contact(new Contact("spy", "http://spy.com", "1727041089@qq.com"))
                .build();
    }
}
