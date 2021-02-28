package com.spy.szse.svc.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;


/**
 * @Author: spy
 * @Date: 2021/2/27 16:35
 */


@Api(tags = "测试模块接口")
@RestController
public class TestController {

    @ApiOperation(value = "获取字符串")
    @GetMapping("/")
    public String getStr() {
        return "hello";
    }
}