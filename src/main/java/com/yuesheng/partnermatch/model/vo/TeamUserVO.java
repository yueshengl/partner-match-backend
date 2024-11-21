package com.yuesheng.partnermatch.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: Dai
 * @Date: 2024/11/20 17:57
 * @Description: TeamUserVo
 * @Version: 1.0
 */
@Data
public class TeamUserVO implements Serializable {

    private static final long serialVersionUID = -5210630113831663684L;
    private Long id;

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
     * 过期时间
     */
    private Date expireTime;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 0 - 公开，1 - 私有，2 - 加密
     */
    private Integer status;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    /**
     * 创建人用户信息
     */
    UserVO createUser;
}
