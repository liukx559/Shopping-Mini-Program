package com.atkexin.ssyx.sys.controller;


import com.atkexin.ssyx.common.result.Result;
import com.atkexin.ssyx.sys.service.RegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 地区表 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2024-03-05
 */
@Api(tags = "区域接口")
@RestController
@RequestMapping("/admin/sys/region")
public class RegionController {

    @Resource
    private RegionService regionService;

    @ApiOperation(value = "根据关键字获取地区列表")
    @GetMapping("findRegionByKeyword/{keyword}")
    public Result findSkuInfoByKeyword(@PathVariable("keyword") String keyword) {
        return Result.ok(regionService.findRegionByKeyword(keyword));
    }
}

