package com.spy.szse.common.enums;

/**
 * @Author lei.zhao_ext
 * @Date 2021/3/1
 */
public enum StatusEnum {

    INVALID(0, "无效状态"),
    VALID(1, "有效状态"),
    PUBLISH(2, "");

    public String getDesc() {
        return desc;
    }

    private Integer value;
    private String desc;

    StatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }
}
