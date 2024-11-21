package com.yuesheng.partnermatch.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuesheng.partnermatch.common.BaseResponse;
import com.yuesheng.partnermatch.common.ErrorCode;
import com.yuesheng.partnermatch.common.ResultUtils;
import com.yuesheng.partnermatch.exception.BusinessException;
import com.yuesheng.partnermatch.model.domain.User;
import com.yuesheng.partnermatch.model.request.UserLoginRequest;
import com.yuesheng.partnermatch.model.request.UserRegisterRequest;
import com.yuesheng.partnermatch.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.yuesheng.partnermatch.constant.UserConstant.USER_LOGIN_STATE;

/**
 * @Author: Dai
 * @Date: 2024/10/31 9:20
 * @Description: UserController 用户接口
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://localhost:5173"})
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate redisTemplate;

    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
        if(userRegisterRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String planetCode = userRegisterRequest.getPlanetCode();
        if(StringUtils.isAnyBlank(userAccount, userPassword, checkPassword,planetCode)){
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        return ResultUtils.success(result);
    }

    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request){
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if(currentUser == null){
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        Long userId = currentUser.getId();
        //TODO:校验用户是否合法
        User user = userService.getById(userId);
        User safeUser = userService.getSafeUser(user);
        return ResultUtils.success(safeUser);
    }


    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request){
        if(userLoginRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if(StringUtils.isAnyBlank(userAccount, userPassword)){
            throw new BusinessException(ErrorCode.NULL_ERROR);
        }
        User user = userService.userLogin(userAccount, userPassword, request);
        return ResultUtils.success(user);
    }

    @GetMapping("/search")
    public BaseResponse<List<User>> searchUsers(String username,HttpServletRequest request){
        //鉴权，仅管理员可查询
        if(!userService.isAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTH,"缺少管理员权限");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(username)){
            queryWrapper.like("username",username);
        }
        List<User> userList = userService.list(queryWrapper);
        List<User> list = userList.stream().map(user -> userService.getSafeUser(user)).collect(Collectors.toList());
        return ResultUtils.success(list);
    }


    @GetMapping("/recommend")
    public BaseResponse<Page<User>> recommendUsers(long pageSize,long pageNum,HttpServletRequest request){
        User loginUser = userService.getLoginUser(request);
        //如果有缓存，直接读缓存
        String redisKey = String.format("partnermatch:user:recommend:%s",loginUser.getId());
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Page<User> userPage = (Page<User>)valueOperations.get(redisKey);
        if(userPage!=null){
            return ResultUtils.success(gerSafeUserPage(userPage));
        }
        //无缓存，查数据库
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        userPage = userService.page(new Page<>(pageNum,pageSize),queryWrapper);
        //写缓存
        try {
            valueOperations.set(redisKey,userPage,1, TimeUnit.DAYS);
        } catch (Exception e) {
            log.error("redis set key error",e);
        }
        // 返回修改后的Page对象
        return ResultUtils.success(gerSafeUserPage(userPage));
    }


    @PostMapping("/update")
    public BaseResponse<Integer> updateUser(@RequestBody User user,HttpServletRequest request){
        //1.校验参数是否为空
        if(user == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //2.鉴权
        User loginUser = userService.getLoginUser(request);
        //3.触发更新
        int result = userService.updateUser(user, loginUser);
        return ResultUtils.success(result);
    }
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody long id,HttpServletRequest request){
        if(!userService.isAdmin(request)){
            throw new BusinessException(ErrorCode.NO_AUTH,"缺少管理员权限");
        }
        if(id<= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = userService.removeById(id);
        return ResultUtils.success(b);
    }


    /**
     * 用户注销
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public BaseResponse<String> userLogout(HttpServletRequest request){
        if(request == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

    @GetMapping("/search/tags")
    public BaseResponse<List<User>> searchUsersByTags(@RequestParam(required = false) List<String> tagNameList){
        if(CollectionUtils.isEmpty(tagNameList)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        List<User> userList = userService.searchUserByTags(tagNameList);
        return ResultUtils.success(userList);
    }

    /**
     * 获取安全的用户列表
     * @param userPage
     * @return
     */
    private Page<User> gerSafeUserPage(Page<User> userPage){
        //修改Page对象中的records
        List<User> records = userPage.getRecords();
        records.replaceAll(user -> {
            // 使用userService.getSafeUser方法处理每个用户
            // 替换原有的用户对象
            return userService.getSafeUser(user);
        });
        return userPage;
    }
}
