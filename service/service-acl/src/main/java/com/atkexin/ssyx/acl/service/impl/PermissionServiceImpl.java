package com.atkexin.ssyx.acl.service.impl;

import com.atkexin.ssyx.acl.helper.PermissionHelper;
import com.atkexin.ssyx.acl.mapper.PermissionMapper;
import com.atkexin.ssyx.acl.service.PermissionService;
import com.atkexin.ssyx.acl.service.RolePermissionService;
import com.atkexin.ssyx.model.acl.Permission;
import com.atkexin.ssyx.model.acl.RolePermission;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService  {
    @Autowired
    private PermissionService permissionService;
    @Override
    public List<Permission> queryAllPermission() {

        List<Permission> allPermission = baseMapper.selectList(null);

        return PermissionHelper.buildPermission(allPermission);
    }



    @Override
    public void removeChildById(Long id) {
        List<Long> idList=new ArrayList<>();
        this.getPermissionById(idList,id);
        idList.add(id);
        permissionService.removeByIds(idList);

    }


    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    //根据角色id查询权限
    public List<Permission> getPermissionById(Long roleId) {
        //根据角色id在RolePermission表中查询，得到RolePermission记录
        List<Permission> allPermission = baseMapper.selectList(null);

        //查询所有权限
        LambdaQueryWrapper<RolePermission> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(RolePermission::getRoleId,roleId);
        List<RolePermission> rolePermission = rolePermissionService.list(wrapper);

        //把rolePermission中的关系型转化为权限Long型
        List<Long> permissionIds
                =rolePermission.stream()
                .map(item->item.getPermissionId())
                .collect(Collectors.toList());
        //在所有菜单中allPermission 中找到角色拥有的菜单rolePermission

        for (Permission permission :allPermission) {
            if(permissionIds.contains(permission.getId()))
            {
                permission.setSelect(true);
                ChildrenSelect(permission,permissionIds);
            }
        }
        return PermissionHelper.buildPermission(allPermission);
    }
    private void ChildrenSelect(Permission permission, List<Long> PermissionIds) {
        if(permission.getChildren()==null) return;
        for(Permission child: permission.getChildren())
        {
            if (PermissionIds.contains(child.getId())) {
            child.setSelect(true);
            }
            ChildrenSelect(child, PermissionIds);
        }
    }

    @Override
    public void assginPermission(Long roleId, List<Long> permissionId) {
        //根据roleId删除对应的permissionList
        LambdaQueryWrapper<RolePermission> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(RolePermission::getRoleId,roleId);
        rolePermissionService.remove(wrapper);
        //根据roleId增加对应的permissionList
        //
        List<RolePermission> assginList=new ArrayList<>();
        for (Long id:permissionId)
        {
            if(StringUtils.isEmpty(id)) continue;
            RolePermission rolePermission = new RolePermission();
            rolePermission.setPermissionId(id);
            rolePermission.setRoleId(roleId);
            assginList.add(rolePermission);
        }
        rolePermissionService.saveBatch(assginList);

    }

    private void getPermissionById(List<Long>idList, Long Pid) {
        LambdaQueryWrapper<Permission> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Permission::getPid,Pid);
        List<Permission> childPermissionList=baseMapper.selectList(wrapper);
        childPermissionList.stream().forEach(item->{
            idList.add(item.getId());
            this.getPermissionById(idList, item.getId());
        });

    }
}
