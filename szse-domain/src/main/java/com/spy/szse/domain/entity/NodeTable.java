package com.spy.szse.domain.entity;

import lombok.Data;

/**
 * @Author lei.zhao_ext
 * @Date 2021/2/26
 */
@Data
public class NodeTable {
    private Long id;
    private Integer versionId;
    private String code;
    private Integer status;
    private Integer level;
    private String source;
    private Integer isLeaf;
    private String ancestors;
    private String name;
    private String remark;
    private Integer sequence;
}
