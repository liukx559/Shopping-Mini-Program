package com.atkexin.ssyx.activity.service;


import com.atkexin.ssyx.model.activity.ActivityInfo;
import com.atkexin.ssyx.model.activity.ActivityRule;
import com.atkexin.ssyx.vo.activity.ActivityRuleVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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

    //查询商品获取规则数据
    List<ActivityRule> findActivityRule(Long skuId);
}
