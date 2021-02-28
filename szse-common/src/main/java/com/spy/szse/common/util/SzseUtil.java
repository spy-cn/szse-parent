package com.spy.szse.common.util;

import com.spy.szse.common.enums.ErrorEnum;
import com.spy.szse.common.exception.SzseException;

/**
 * 工具类
 *
 * @Author: spy
 * @Date: 2021/2/28 15:57
 */

public class SzseUtil {
    public static void isBlankException(boolean expression,String message){
        if (expression){
            throw new SzseException(ErrorEnum.ERROR_NOT_EMPTY,message);
        }

    }
}
