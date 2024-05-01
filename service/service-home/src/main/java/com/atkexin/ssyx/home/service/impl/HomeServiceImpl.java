package com.atkexin.ssyx.home.service.impl;

import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.atkexin.ssyx.activity.client.ActivityFeignClient;
import com.atkexin.ssyx.client.user.UserFeignClient;
import com.atkexin.ssyx.home.service.HomeService;
import com.atkexin.ssyx.model.product.Category;
import com.atkexin.ssyx.model.product.SkuInfo;
import com.atkexin.ssyx.model.search.SkuEs;
import com.atkexin.ssyx.product.client.ProductFeignClient;
import com.atkexin.ssyx.search.client.SearchFeignClient;
import com.atkexin.ssyx.vo.user.LeaderAddressVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private ProductFeignClient productFeignClient;
    @Autowired
    private SearchFeignClient searchFeignClient;
    @Autowired
    private ActivityFeignClient activityFeignClient;
    @Override
    public Map<String, Object> home(Long userId) {
        Map<String, Object> result=new HashMap<>();
        //获取提货点user
        LeaderAddressVo userAddressByUserId = userFeignClient.getUserAddressByUserId(userId);
        result.put("LeaderAddressVo",userAddressByUserId);
        //获取分类product
        List<Category> categoryList = productFeignClient.findAllCategoryList();
        result.put("categoryList",categoryList);
        //新人商品product
        List<SkuInfo> newPersonList=productFeignClient.findNewPersonSkuInfoList();
        result.put("newPersonList",newPersonList);

        //提货点地址信息
        LeaderAddressVo leaderAddressVo = userFeignClient.getUserAddressByUserId(userId);
        result.put("leaderAddressVo", leaderAddressVo);

        //获取爆品商品es

        List<SkuEs> hotSkuList = searchFeignClient.findHotSkuList();
        //获取sku对应的促销活动标签
        if(!CollectionUtils.isEmpty(hotSkuList)) {
            List<Long> skuIdList = hotSkuList.stream().map(sku -> sku.getId()).collect(Collectors.toList());
            Map<Long, List<String>> skuIdToRuleListMap = activityFeignClient.findActivity(skuIdList);
            if(null != skuIdToRuleListMap) {
                hotSkuList.forEach(skuEs -> {
                    skuEs.setRuleList(skuIdToRuleListMap.get(skuEs.getId()));
                });
            }
        }
        result.put("hotSkuList", hotSkuList);
        return result;

    }
}
