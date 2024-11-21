package com.yuesheng.partnermatch.model.request;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 队伍
 * @TableName team
 */
@Data
public class TeamJoinRequest implements Serializable {
    private static final long serialVersionUID = 4009154742727041643L;
    /**
     * id
     */
    private Long teamId;

    /**
     * 密码
     */
    private String password;
}