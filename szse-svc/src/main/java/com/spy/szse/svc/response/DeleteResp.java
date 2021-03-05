package com.spy.szse.svc.response;

import lombok.Data;

/**
 * 删除操作返回类
 *
 * @Author lei.zhao_ext
 * @Date 2021/3/4
 */
@Data
public class DeleteResp {
    /**
     * 消耗时间
     */
    private long took;
    /**
     * 营销行数
     */
    private int result;
}
