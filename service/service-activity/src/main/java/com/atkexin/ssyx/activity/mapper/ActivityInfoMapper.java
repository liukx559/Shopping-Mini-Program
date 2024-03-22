package com.atkexin.ssyx.activity.mapper;


import com.atkexin.ssyx.model.activity.ActivityInfo;
import com.atkexin.ssyx.model.activity.ActivityRule;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 活动表 Mapper 接口
 * </p>
 *
 * @author atkexin
 * @since 2024-03-14
 */
public interface ActivityInfoMapper extends BaseMapper<ActivityInfo> {

    List<Long> selectExistSkuIdList(List<Long> skuIdList);

    List<ActivityRule> selectActivityRuleList(Long skuId);
}
