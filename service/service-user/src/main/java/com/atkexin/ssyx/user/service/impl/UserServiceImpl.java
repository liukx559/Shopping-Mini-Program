package com.atkexin.ssyx.user.service.impl;

import com.atkexin.ssyx.enums.UserType;
import com.atkexin.ssyx.model.user.Leader;
import com.atkexin.ssyx.model.user.User;
import com.atkexin.ssyx.model.user.UserDelivery;
import com.atkexin.ssyx.user.mapper.LeaderMapper;
import com.atkexin.ssyx.user.mapper.UserDeliveryMapper;
import com.atkexin.ssyx.user.mapper.UserMapper;
import com.atkexin.ssyx.region.client.RegionFeignClient;
import com.atkexin.ssyx.user.service.UserService;
import com.atkexin.ssyx.vo.user.LeaderAddressVo;
import com.atkexin.ssyx.vo.user.UserLoginVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    @Autowired
    private UserMapper userMapper;

    @Resource
    private UserDeliveryMapper userDeliveryMapper;

    @Resource
    private LeaderMapper leaderMapper;

    @Resource
    private RegionFeignClient regionFeignClient;

    //根据userid查询团长和自提点信息
    @Override
    public LeaderAddressVo getLeaderAddressVoByUserId(Long userId) {
        LambdaQueryWrapper<UserDelivery> queryWrapper = new LambdaQueryWrapper<>();
        //默认自提点
        queryWrapper.eq(UserDelivery::getUserId, userId);
        queryWrapper.eq(UserDelivery::getIsDefault, 1);
        UserDelivery userDelivery = userDeliveryMapper.selectOne(queryWrapper);
        if(null == userDelivery) return null;
        //默认团长
        Leader leader = leaderMapper.selectById(userDelivery.getLeaderId());
        LeaderAddressVo leaderAddressVo = new LeaderAddressVo();
        BeanUtils.copyProperties(leader, leaderAddressVo);
        leaderAddressVo.setUserId(userId);
        leaderAddressVo.setLeaderId(leader.getId());
        leaderAddressVo.setLeaderName(leader.getName());
        leaderAddressVo.setLeaderPhone(leader.getPhone());
        leaderAddressVo.setWareId(userDelivery.getWareId());
        leaderAddressVo.setStorePath(leader.getStorePath());
        return leaderAddressVo;
    }

    @Override
    public User getByOpenid(String openId) {
        return userMapper.selectOne(new QueryWrapper<User>().eq("open_id", openId));
    }

    @Override
    public UserLoginVo getUserLoginVo(Long userId) {
        UserLoginVo userLoginVo = new UserLoginVo();
        User user = this.getById(userId); //this=userService
        userLoginVo.setNickName(user.getNickName());
        userLoginVo.setUserId(userId);
        userLoginVo.setPhotoUrl(user.getPhotoUrl());
        userLoginVo.setOpenId(user.getOpenId());
        userLoginVo.setIsNew(user.getIsNew());

        //如果是团长获取当前前团长id与对应的仓库id
        if(user.getUserType() == 1) {
            LambdaQueryWrapper<Leader> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Leader::getUserId, userId);
            queryWrapper.eq(Leader::getCheckStatus, 1);
            Leader leader = leaderMapper.selectOne(queryWrapper);
            if(null != leader) {
                userLoginVo.setLeaderId(leader.getId());
                Long wareId = regionFeignClient.getWareId(leader.getRegionId());
                userLoginVo.setWareId(wareId);
            }
        } else {
            //如果是会员获取当前会员对应的仓库id
            LambdaQueryWrapper<UserDelivery> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(UserDelivery::getUserId, userId);
            queryWrapper.eq(UserDelivery::getIsDefault, 1);
            UserDelivery userDelivery = userDeliveryMapper.selectOne(queryWrapper);
            if(null != userDelivery) {
                userLoginVo.setLeaderId(userDelivery.getLeaderId());
                userLoginVo.setWareId(userDelivery.getWareId());
            } else {
                userLoginVo.setLeaderId(1L);
                userLoginVo.setWareId(1L);
            }
        }
        return userLoginVo;
    }
}