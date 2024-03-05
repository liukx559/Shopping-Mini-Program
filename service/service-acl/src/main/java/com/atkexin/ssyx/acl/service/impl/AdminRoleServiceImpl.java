package com.atkexin.ssyx.acl.service.impl;

import com.atkexin.ssyx.acl.mapper.AdminRoleMapper;
import com.atkexin.ssyx.acl.service.AdminRoleService;
import com.atkexin.ssyx.model.acl.AdminRole;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
//implements实现接口中定义的所有方法
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper,AdminRole> implements AdminRoleService{
}
