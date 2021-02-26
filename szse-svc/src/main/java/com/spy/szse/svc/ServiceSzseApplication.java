package com.spy.szse.svc;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author lei.zhao_ext
 * @Date 2021/2/26
 */
@EnableKnife4j
@SpringBootApplication
public class ServiceSzseApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceSzseApplication.class, args);
    }
}
