package com.atkexin.ssyx.activity.mapper;


import com.atkexin.ssyx.model.activity.ActivityInfo;
import com.atkexin.ssyx.model.activity.ActivityRule;
import com.atkexin.ssyx.model.activity.ActivitySku;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import feign.Param;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <p>
 * 活动表 Mapper 接口
 * </p>
 *
 * @author atkexin
 * @since 2024-03-14
 */
@Mapper
public interface ActivityInfoMapper extends BaseMapper<ActivityInfo> {

    List<Long> selectExistSkuIdList(List<Long> skuIdList);

    List<ActivityRule> selectActivityRuleList(@Param("skuId")Long skuId);


    List<ActivitySku> selectCartActivityList(List<Long> skuIdList);
}
