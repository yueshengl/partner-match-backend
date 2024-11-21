package com.yuesheng.partnermatch.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuesheng.partnermatch.model.domain.UserTeam;
import com.yuesheng.partnermatch.mapper.UserTeamMapper;
import com.yuesheng.partnermatch.service.UserTeamService;
import org.springframework.stereotype.Service;

/**
* @author shengyu dai
* @description 针对表【user_team(用户队伍关系)】的数据库操作Service实现
* @createDate 2024-11-19 18:58:39
*/
@Service
public class UserTeamServiceImpl extends ServiceImpl<UserTeamMapper, UserTeam>
    implements UserTeamService{

}




