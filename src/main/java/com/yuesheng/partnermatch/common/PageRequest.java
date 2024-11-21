package com.yuesheng.partnermatch.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Dai
 * @Date: 2024/11/19 22:20
 * @Description: PageRequest
 * @Version: 1.0
 */
@Data
public class PageRequest implements Serializable {

    private static final long serialVersionUID = -140837815837837L;
    /**
     * 页面大小
     */
    protected int pageSize=10;

    /**
     * 当前第几页
     */
    protected int pageNum=1;
}
