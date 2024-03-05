package com.atkexin.ssyx.acl.controller;

import com.atkexin.ssyx.acl.service.PermissionService;
import com.atkexin.ssyx.acl.service.RolePermissionService;
import com.atkexin.ssyx.common.result.Result;
import com.atkexin.ssyx.model.acl.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//描述控制层(Controller)并返回JSON数据类型，但不会再执行配置的视图解析器，也不会返回给jsp页面，返回值就是return里的内容。
@RequestMapping("/admin/acl/permission")//接口地址//通过"/xxx"来指定控制器可以处理哪些URL请求
@CrossOrigin
@Api(tags = "菜单接口")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @GetMapping
    @ApiOperation("获取权限(菜单/功能)列表")
    public Result getPermissionList()
    {
       List<Permission> list=permissionService.queryAllPermission();
       return Result.ok(list);


    }
    //添加菜单
//    url: `${api_name}/save`,
//    method: "post",
//    data: permission
    @PostMapping("/save")
    @ApiOperation("保存一个权限项")
    public Result addPermission(@RequestBody Permission permission)
    {
       permissionService.save(permission);
       return Result.ok(null);
    }
    //删除菜单
//     return request({
//        url: `${api_name}/remove/${id}`,
//        method: "delete"
    @DeleteMapping("/remove/{id}")
    @ApiOperation("删除一个权限项")
    public Result deletePermission(@PathVariable Long id)//{id}
    {
        permissionService.removeChildById(id);


        return Result.ok(null);
    }
    //修改菜单
//    return request({
//        url: `${api_name}/update`,
//        method: "put",
//                data: permission
    @PutMapping("/update")
    @ApiOperation("修改一个权限项")
    public Result updatePermission(@RequestBody Permission permission)//data
    {
        permissionService.updateById(permission);
        return Result.ok(null);
    }
//     return request({
//        url: `${api_name}/toAssign/${roleId}`,
//        method: 'get'
    @GetMapping("/toAssign/{roleId}")
    @ApiOperation("查询权限")
    public Result toAssign(@PathVariable Long roleId)
    {
        //List<Permission> allPermissionList=permissionService.queryAllPermission();
        List<Permission> permissionList = permissionService.getPermissionById(roleId);

        return Result.ok(permissionList);
    }
    //分配角色权限
//    url: `${api_name}/doAssign`,
//    method: "post",
//    params: {roleId, permissionId}

    @Autowired
    private RolePermissionService rolePermissionService;
    @PostMapping("/doAssign")
    @ApiOperation("分配角色权限")
    public Result doassign(@RequestParam Long roleId,@RequestParam List<Long> permissionId)
    {
        permissionService.assginPermission(roleId,permissionId);
        return Result.ok(null);
    }
}
