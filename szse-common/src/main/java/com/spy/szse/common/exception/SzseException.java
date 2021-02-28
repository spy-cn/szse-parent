package com.spy.szse.common.exception;

import com.spy.szse.common.enums.ErrorEnum;

/**
 * 异常类
 *
 * @Author: spy
 * @Date: 2021/2/28 17:50
 */

public class SzseException extends RuntimeException {
    private ErrorEnum errorEnum;

    public SzseException(ErrorEnum errorEnum) {
        super(errorEnum.getErrorMessage());
        this.errorEnum = errorEnum;
    }

    public SzseException(ErrorEnum errorEnum, Object... params) {
        super(errorEnum.format(params));
        this.errorEnum = errorEnum;
    }
}
