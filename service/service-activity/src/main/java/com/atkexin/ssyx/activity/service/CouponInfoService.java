package com.atkexin.ssyx.activity.service;

import com.atkexin.ssyx.model.activity.CouponInfo;
import com.atkexin.ssyx.vo.activity.CouponRuleVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 优惠券信息 服务类
 * </p>
 *
 * @author atkexin
 * @since 2024-03-14
 */
public interface CouponInfoService extends IService<CouponInfo> {

    IPage<CouponInfo> selectPage(Page<CouponInfo> pageParam);

    CouponInfo getCouponInfo(String id);

    Object findCouponRuleList(Long id);

    void saveCouponRule(CouponRuleVo couponRuleVo);

    Object findCouponByKeyword(String keyword);


   List<CouponInfo> findCouponInfoList(long skuId, long userId);
}
