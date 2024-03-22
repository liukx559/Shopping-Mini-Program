package com.atkexin.ssyx.activity.service;

import com.atkexin.ssyx.model.activity.SeckillSku;
import com.atkexin.ssyx.vo.activity.SeckillSkuQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 秒杀活动商品关联 服务类
 * </p>
 *
 * @author atkexin
 * @since 2024-03-14
 */
public interface SeckillSkuService extends IService<SeckillSku> {


    IPage<SeckillSku> selectPage(Page<SeckillSku> pageParam, SeckillSkuQueryVo seckillSkuQueryVo);
}
