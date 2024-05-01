package com.atkexin.ssyx.activity.mapper;


import com.atkexin.ssyx.model.activity.CouponInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * <p>
 * 优惠券信息 Mapper 接口
 * </p>
 *
 * @author atkexin
 * @since 2024-03-14
 */
public interface CouponInfoMapper extends BaseMapper<CouponInfo> {

    List<CouponInfo> selectCouponInfoList(@Param("skuId") long skuId, @Param("categoryId")Long categoryId, @Param(" userId") long userId);
}
