package com.atkexin.ssyx.acl.helper;

import com.atkexin.ssyx.model.acl.Permission;

import java.util.ArrayList;
import java.util.List;

public class  PermissionHelper {
    public static List<Permission> buildPermission(List<Permission> allList) {
        List<Permission> tree=new ArrayList<>();//??
        for (Permission permission:allList)
        {
            if(permission.getPid()==0)
            {
                permission.setLevel(1);
                tree.add(findChildPermission(permission,allList));
            }
        }
        return tree;
    }

    private static Permission findChildPermission(Permission permission, List<Permission> allList) {
        permission.setChildren(new ArrayList<>());
        for (Permission it : allList) {
            if (it.getPid() == permission.getId()) {
                it.setLevel(permission.getLevel() + 1);
                if(permission.getChildren()==null)
                    permission.setChildren(new ArrayList<>());
                permission.getChildren().add(findChildPermission(it,allList));
            }

        }
        return permission;//返回本身来添加
    }

}
