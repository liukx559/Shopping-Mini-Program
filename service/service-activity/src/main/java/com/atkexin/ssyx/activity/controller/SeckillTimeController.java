package com.atkexin.ssyx.activity.controller;


import com.atkexin.ssyx.activity.service.SeckillTimeService;
import com.atkexin.ssyx.common.result.Result;
import com.atkexin.ssyx.model.activity.SeckillTime;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 秒杀活动场次 前端控制器
 * </p>
 *
 * @author atkexin
 * @since 2024-03-14
 */
@Api(value = "秒杀场次管理", tags = "秒杀场次管理")
@RestController
@RequestMapping(value="/admin/activity/seckillTime")
@SuppressWarnings({"unchecked", "rawtypes"})
public class SeckillTimeController {

    @Resource
    private SeckillTimeService seckillTimeService;

    @ApiOperation(value = "获取分页列表")
    @GetMapping()
    public Result index() {
        return Result.ok(seckillTimeService.list());
    }

    @ApiOperation(value = "获取")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        SeckillTime seckillSession = seckillTimeService.getById(id);
        return Result.ok(seckillSession);
    }

    @ApiOperation(value = "新增")
    @PostMapping("save")
    public Result save(@RequestBody SeckillTime seckillSession) {
        seckillTimeService.save(seckillSession);
        return Result.ok(null);
    }

    @ApiOperation(value = "修改")
    @PutMapping("update")
    public Result updateById(@RequestBody SeckillTime seckillSession) {
        seckillTimeService.updateById(seckillSession);
        return Result.ok(null);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id) {
        seckillTimeService.removeById(id);
        return Result.ok(null);
    }

    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        seckillTimeService.removeByIds(idList);
        return Result.ok(null);
    }

    @ApiOperation(value = "更新状态")
    @PostMapping("updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Long id, @PathVariable Integer status) {
        seckillTimeService.updateStatus(id, status);
        return Result.ok(null);
    }
}

