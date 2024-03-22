package com.atkexin.ssyx.activity.mapper;


import com.atkexin.ssyx.model.activity.SeckillSku;
import com.atkexin.ssyx.vo.activity.SeckillSkuVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 秒杀活动商品关联 Mapper 接口
 * </p>
 *
 * @author atkexin
 * @since 2024-03-14
 */
public interface SeckillSkuMapper extends BaseMapper<SeckillSku> {

    List<SeckillSkuVo> findSeckillSkuListByDate(String date);
}
