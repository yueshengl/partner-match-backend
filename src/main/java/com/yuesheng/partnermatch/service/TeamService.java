package com.yuesheng.partnermatch.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuesheng.partnermatch.model.domain.Team;
import com.yuesheng.partnermatch.model.domain.User;
import com.yuesheng.partnermatch.model.dto.TeamQuery;
import com.yuesheng.partnermatch.model.request.TeamJoinRequest;
import com.yuesheng.partnermatch.model.request.TeamUpdateRequest;
import com.yuesheng.partnermatch.model.vo.TeamUserVO;

import java.util.List;

/**
* @author shengyu dai
* @description 针对表【team(队伍)】的数据库操作Service
* @createDate 2024-11-19 18:57:17
*/
public interface TeamService extends IService<Team> {


    /**
     * 创建队伍
     * @param team
     * @param loginUser
     * @return
     */
    long addTeam(Team team, User loginUser);

    /**
     * 搜索队伍
     *
     * @param teamQuery
     * @param isAdmin
     * @return
     */
    List<TeamUserVO> listTeams(TeamQuery teamQuery, boolean isAdmin);

    /**
     * 更新队伍信息
     * @param teamUpdateRequest
     * @param loginUser
     * @return
     */
    boolean updatTeam(TeamUpdateRequest teamUpdateRequest,User loginUser);

    /**
     * 加入队伍
     * @param teamJoinRequest
     * @param loginUser
     * @return
     */
    boolean joinTeam(TeamJoinRequest teamJoinRequest, User loginUser);
}
