package com.spy.szse.common.util;

/**
 * 一个请求工具类，主要用来获取username
 *
 * @Author lei.zhao_ext
 * @Date 2021/3/3
 */
public class SzseHttpRequestUtil {
    private static final ThreadLocal<String> USERNAME = new ThreadLocal<>();

    public static String getUsername() {
        return SzseHttpRequestUtil.USERNAME.get();
    }

    public static void setUsername(String username) {
        SzseHttpRequestUtil.setUsername(username);
    }
}
