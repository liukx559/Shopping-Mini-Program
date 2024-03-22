package com.atkexin.ssyx.activity.controller;


import com.atkexin.ssyx.activity.service.SeckillSkuService;
import com.atkexin.ssyx.common.result.Result;
import com.atkexin.ssyx.model.activity.SeckillSku;
import com.atkexin.ssyx.vo.activity.SeckillSkuQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 秒杀活动商品关联 前端控制器
 * </p>
 *
 * @author atkexin
 * @since 2024-03-14
 */
@Api(value = "SeckillSku管理", tags = "SeckillSku管理")
@RestController
@RequestMapping(value="/admin/activity/seckillSku")
@SuppressWarnings({"unchecked", "rawtypes"})
public class SeckillSkuController {

    @Resource
    private SeckillSkuService seckillSkuService;

    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "seckillSkuQueryVo", value = "查询对象", required = false)
            SeckillSkuQueryVo seckillSkuQueryVo) {
        Page<SeckillSku> pageParam = new Page<>(page, limit);
        IPage<SeckillSku> pageModel = seckillSkuService.selectPage(pageParam, seckillSkuQueryVo);
        return Result.ok(pageModel);
    }

    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        SeckillSku seckillSku = seckillSkuService.getById(id);
        return Result.ok(seckillSku);
    }

    @ApiOperation(value = "新增")
    @PostMapping("save")
    public Result save(@RequestBody List<SeckillSku> seckillSkuList) {
        seckillSkuService.saveBatch(seckillSkuList);
        return Result.ok(null);
    }

    @ApiOperation(value = "修改")
    @PutMapping("update")
    public Result updateById(@RequestBody SeckillSku seckillSku) {
        seckillSkuService.updateById(seckillSku);
        return Result.ok(null);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        seckillSkuService.removeById(id);
        return Result.ok(null);
    }

    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        seckillSkuService.removeByIds(idList);
        return Result.ok(null);
    }
}

