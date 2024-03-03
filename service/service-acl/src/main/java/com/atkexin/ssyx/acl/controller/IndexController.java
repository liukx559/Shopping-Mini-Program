package com.atkexin.ssyx.acl.controller;

import com.atkexin.ssyx.common.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController//描述控制层(Controller)并返回JSON数据类型，但不会再执行配置的视图解析器，也不会返回给jsp页面，返回值就是return里的内容。
@RequestMapping("/admin/acl/index/")//接口地址//通过"/xxx"来指定控制器可以处理哪些URL请求
@CrossOrigin
public class IndexController {
    //login接口
    @ApiOperation("登录")
    @PostMapping("login")
    public Result login()
    {
        Map<String,Object> map = new HashMap<>();
        map.put("token","admin-token");
        return Result.ok(map);
    }
    @ApiOperation("获取信息")
    @GetMapping("info")
    public Result getInfo()
    {
        Map<String,Object> map = new HashMap<>();
        map.put("name","atkexin");
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.ok(map);
    }
    @ApiOperation("退出")
    @PostMapping("logout")
    public Result logout()
    {
        return Result.ok(null);
    }
}
