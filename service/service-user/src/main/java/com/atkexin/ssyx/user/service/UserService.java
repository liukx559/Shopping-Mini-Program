package com.atkexin.ssyx.user.service;

import com.atkexin.ssyx.enums.user.User;
import com.atkexin.ssyx.vo.user.LeaderAddressVo;
import com.atkexin.ssyx.vo.user.UserLoginVo;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {
    User getByOpenid(String openId);


    LeaderAddressVo getLeaderAddressVoByUserId(Long id);

    UserLoginVo getUserLoginVo(Long id);
}
