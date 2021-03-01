package com.spy.szse.common.util;

import com.spy.szse.common.enums.ErrorEnum;
import com.spy.szse.common.exception.SzseException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * 工具类
 *
 * @Author: spy
 * @Date: 2021/2/28 15:57
 */

public class SzseUtil {
    /**
     * 单个空异常信息
     *
     * @param expression
     * @param message
     */
    public static void isBlankException(boolean expression, String message) {
        if (expression) {
            throw new SzseException(ErrorEnum.ERROR_NOT_EMPTY, message);
        }
    }

    /**
     * 任意为空
     *
     * @param params
     */
    public static boolean anyBlank(Object... params) {
        Predicate<Object> filter = x ->
                x instanceof Collection ?
                        !CollectionUtils.isEmpty((Collection) x) :
                        (x instanceof String ? StringUtils.isNotBlank((String) x) : Objects.nonNull(x));
        return Arrays.asList(params).stream().filter(filter).count() < params.length;
    }

    /**
     * 数据不合法异常信息
     *
     * @param expression
     * @param message
     */
    public static void illegalException(boolean expression, String message) {
        if (expression) {
            throw new SzseException(ErrorEnum.ERROR_ILLEGAL_DATA, message);
        }
    }
}
