package com.atkexin.ssyx.sys.controller;


import com.atkexin.ssyx.common.result.Result;
import com.atkexin.ssyx.sys.service.WareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 仓库表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2024-03-05
 */
@Api(value = "Ware管理", tags = "Ware管理")
@RestController
@RequestMapping(value="/admin/sys/ware")
public class WareController {

    @Resource
    private WareService wareService;

    @ApiOperation(value = "获取全部仓库")
    @GetMapping("findAllList")
    public Result findAllList() {
        return Result.ok(wareService.list());
    }
}

