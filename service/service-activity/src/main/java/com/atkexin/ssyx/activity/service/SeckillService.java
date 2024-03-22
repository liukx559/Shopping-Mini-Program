package com.atkexin.ssyx.activity.service;

import com.atkexin.ssyx.model.activity.Seckill;
import com.atkexin.ssyx.vo.activity.SeckillQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 秒杀活动 服务类
 * </p>
 *
 * @author atkexin
 * @since 2024-03-14
 */
public interface SeckillService extends IService<Seckill> {

    IPage<Seckill> selectPage(Page<Seckill> pageParam, SeckillQueryVo seckillQueryVo);

    void updateStatus(Long id, Integer status);
}
