package com.atkexin.ssyx.acl.service.impl;

import com.atkexin.ssyx.acl.mapper.RoleMapper;
import com.atkexin.ssyx.acl.service.AdminRoleService;
import com.atkexin.ssyx.acl.service.RoleService;
import com.atkexin.ssyx.model.acl.AdminRole;
import com.atkexin.ssyx.model.acl.Role;
import com.atkexin.ssyx.vo.acl.RoleQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {

    @Autowired
    private AdminRoleService adminRoleService;//查询用户角色表需要用到的
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

    @Override
    public Map<String, Object> getRoleByadminId(Long id) {
        List<Role> allRolesList=baseMapper.selectList(null);
        Map<String, Object> map=new HashMap<>();
        //根据id查询admin_role表
        //条件构造器
        LambdaQueryWrapper<AdminRole> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(AdminRole::getAdminId,id);

        List<AdminRole> adminRoleList=adminRoleService.list(wrapper);
        //封装成角色id列表
        List<Long> existRoleIdList = adminRoleList.stream().map(item->item.getRoleId()).collect(Collectors.toList());
        //根据角色id列表在allRolesList查询是否存在
        List<Role> assignRoleList=new ArrayList<>();
        for (Role role:allRolesList) {
            if(existRoleIdList.contains(role.getId()))
            {
                assignRoleList.add(role);
            }

        }
        map.put("allRolesList", allRolesList);
        map.put("assignRoles", assignRoleList);

        return map;
    }
    @Override
    public void assignAdminRole(Long adminId, List<Long> roleIds) {
        //删除
        LambdaQueryWrapper<AdminRole> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(AdminRole::getAdminId,adminId);
        adminRoleService.remove(wrapper);
        //分配新的角色
        List<AdminRole> userRoleList = new ArrayList<>();
        for(Long roleId : roleIds) {
            if(StringUtils.isEmpty(roleId)) continue;
            AdminRole userRole = new AdminRole();
            userRole.setAdminId(adminId);
            userRole.setRoleId(roleId);
            userRoleList.add(userRole);
        }
        adminRoleService.saveBatch(userRoleList);

    }



}
