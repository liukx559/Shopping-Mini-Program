package com.atkexin.ssyx.home.controller;

import com.atkexin.ssyx.common.auth.AuthContextHolder;
import com.atkexin.ssyx.common.result.Result;
import com.atkexin.ssyx.home.service.HomeService;
import com.atkexin.ssyx.home.service.impl.HomeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
//TODO: 更改前端路径
@Api(tags = "首页接口")
@RequestMapping("/api/home/")
@RestController
public class HomeApiController {
    @Autowired
    private HomeService homeService;



    @ApiOperation(value = "获取首页数据")
    @GetMapping("/index")
    public Result index(HttpServletRequest request) {
        // 获取用户Id
        Long userId = AuthContextHolder.getUserId();//从ThreadLoCal中获取用户id
        return Result.ok(homeService.home(userId));
    }


}
