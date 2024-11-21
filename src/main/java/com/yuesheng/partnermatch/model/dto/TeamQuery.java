package com.yuesheng.partnermatch.model.dto;

import com.yuesheng.partnermatch.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;

/**
 * @Author: Dai
 * @Date: 2024/11/19 22:06
 * @Description: TeamQuery
 * @Version: 1.0
 */
//队伍查询封装类
@EqualsAndHashCode(callSuper = true)
@Data
public class TeamQuery extends PageRequest {

    private Long id;

    /**
     * 搜索关键词（同时对队伍名称和描述进行搜索）
     */
    private String searchText;

    /**
     * 队伍名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 最大人数
     */
    private Integer maxNum;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 0 - 公开，1 - 私有，2 - 加密
     */
    private Integer status;

}
