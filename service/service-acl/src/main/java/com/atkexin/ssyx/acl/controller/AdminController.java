package com.atkexin.ssyx.acl.controller;

import com.atkexin.ssyx.acl.service.AdminService;
import com.atkexin.ssyx.acl.service.RoleService;
import com.atkexin.ssyx.common.result.Result;
import com.atkexin.ssyx.common.utils.MD5;
import com.atkexin.ssyx.model.acl.Admin;
import com.atkexin.ssyx.vo.acl.AdminQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户管理 前端控制器
 */
@RestController
@RequestMapping("/admin/acl/user")
@Api(tags = "用户管理")
@CrossOrigin //跨域
public class AdminController {
//    @Autowired如果找到了与注入对象类型匹配的Bean对象，则会将其注入到对应的属性或构造方法参数中。如果找不到匹配的Bean对象，则会抛出异常。
    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;
    //用户的条件分页查询
    @ApiOperation(value = "获取管理用户分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "userQueryVo", value = "查询对象", required = false)//条件对象
            AdminQueryVo userQueryVo) {
        //分页过程，newpage-调用service方法-返回IPage-return result
        Page<Admin> pageParam = new Page<>(page, limit);
        IPage<Admin> pageModel = adminService.selectPage(pageParam, userQueryVo);
        return Result.ok(pageModel);
    }

    @ApiOperation(value = "获取管理用户")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        Admin admin = adminService.getById(id);
        return Result.ok(admin);
    }
//    url: `${api_name}/save`,
//    method: 'post',
//    data: user//json格式传数据
    @ApiOperation(value = "新增管理用户")
    @PostMapping("save")//与前端一致
    public Result save(@RequestBody Admin user) {
        //对密码进行MD5处理
        String ps=user.getPassword();
        String psMD5=MD5.encrypt(ps);
        user.setPassword(psMD5);
        adminService.save(user);
        return Result.ok(null);
    }

    @ApiOperation(value = "修改管理用户")
    @PutMapping("update")
    //@RequestBody用于获取HTTP请求体中的数据，并将其转化为指定的Java对象。
    public Result updateById(@RequestBody Admin user) {
        adminService.updateById(user);
        return Result.ok(null);
    }

    @ApiOperation(value = "删除管理用户")
    @DeleteMapping("remove/{id}")
//    url: `${api_name}/remove/${id}`,
//    method: 'delete'
    //@PathVariable是Spring框架中的注解，用于获取URL中的参数值。
    public Result remove(@PathVariable Long id) {
        adminService.removeById(id);
        return Result.ok(null);
    }
//    url: `${api_name}/batchRemove`,
//    method: 'delete',
//    data: ids
    @ApiOperation(value = "批量删除管理用户")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        adminService.removeByIds(idList);
        return Result.ok(null);
    }

    @ApiOperation(value = "根据用户id查询角色")
    @GetMapping("/toAssign/{adminId}")//与url一致
    public Result toAssign(@PathVariable Long adminId)
    {
      Map<String,Object> map=roleService.getRoleByadminId(adminId);
      return Result.ok(map);
    }
    @ApiOperation(value = "修改用户角色")
    @PostMapping("/doAssign")
    public Result doAssign(@RequestParam Long adminId,@RequestParam List<Long> roleId)
    {
        roleService.assignAdminRole(adminId,roleId);
        return Result.ok(null);
    }
}
