package com.atkexin.ssyx.activity.service;


import com.atkexin.ssyx.model.activity.ActivityInfo;
import com.atkexin.ssyx.model.activity.ActivityRule;
import com.atkexin.ssyx.model.order.CartInfo;
import com.atkexin.ssyx.vo.activity.ActivityRuleVo;
import com.atkexin.ssyx.vo.order.CartInfoVo;
import com.atkexin.ssyx.vo.order.OrderConfirmVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 活动表 服务类
 * </p>
 *
 * @author atkexin
 * @since 2024-03-14
 */
public interface ActivityInfoService extends IService<ActivityInfo> {

    IPage<com.atkexin.ssyx.model.activity.ActivityInfo> selectPage(Page<com.atkexin.ssyx.model.activity.ActivityInfo> pageParam);

    Object findActivityRuleList(Long id);

    void saveActivityRule(ActivityRuleVo activityRuleVo);

    Object findSkuInfoByKeyword(String keyword);

    public List<ActivityRule> findActivityRule(Long skuId);

    Map<Long, List<String>> findActivity(List<Long> skuIdList);

    Map<String, Object> findActivityAndCoupon(long skuId, long userId);

    OrderConfirmVo findCartActivityAndCoupon(List<CartInfo> cartInfoList, Long userId);
    List<CartInfoVo> findCartActivityList(List<CartInfo> cartInfoList);
}
