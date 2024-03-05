package com.atkexin.ssyx.acl.service;

import com.atkexin.ssyx.model.acl.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface PermissionService extends IService<Permission> {
    List<Permission> queryAllPermission();

    void removeChildById(Long id);

    public List<Permission> getPermissionById(Long roleId);

    void assginPermission(Long roleId, List<Long> permissionId);
}
