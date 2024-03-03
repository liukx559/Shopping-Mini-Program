package com.atkexin.ssyx.acl.service.impl;

import com.atkexin.ssyx.acl.mapper.RoleMapper;
import com.atkexin.ssyx.acl.service.RoleService;
import com.atkexin.ssyx.model.acl.Role;
import com.atkexin.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {

    //角色分页列表
    @Override
    public IPage<Role> selectPage(Page<Role> pageParam, @NotNull RoleQueryVo roleQueryVo) {
        //获取条件值：角色名称
        String roleName = roleQueryVo.getRoleName();
        //创建条件构造器对象
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        //判断条件值是否为空
        if(!StringUtils.isEmpty(roleName)) {
            //封装条件
            wrapper.like(Role::getRoleName,roleName);
        }
        //调用mapper方法实现条件分页查询
        IPage<Role> pageModel = baseMapper.selectPage(pageParam, wrapper);
        return pageModel;
    }

}
