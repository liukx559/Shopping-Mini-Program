package com.atkexin.ssyx.home.service.impl;

import com.atkexin.ssyx.activity.client.ActivityFeignClient;
import com.atkexin.ssyx.home.config.ThreadPoolConfig;
import com.atkexin.ssyx.home.service.ItemService;
import com.atkexin.ssyx.product.client.ProductFeignClient;
import com.atkexin.ssyx.search.client.SearchFeignClient;
import com.atkexin.ssyx.vo.product.SkuInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class ItemServiceimpl implements ItemService {
    @Autowired
    private ProductFeignClient productFeignClient;
    @Autowired
    private ActivityFeignClient activityFeignClient;
    @Resource
    private ThreadPoolExecutor executor;

    @Autowired
    private SearchFeignClient searchFeignClient;

    @Override
    public Map<String,Object> item(Long id, Long userId) {
        Map<String,Object> res=new HashMap<>();
        CompletableFuture<Void> skuInfoVoCompletableFuture =CompletableFuture.runAsync(()->{
            //远程调用
            //sku信息
            SkuInfoVo skuInfoVo=productFeignClient.getSkuInfovo(id);
            res.put("skuInfoVo",skuInfoVo);

        },executor);
        CompletableFuture<Void> activityompletable= CompletableFuture.runAsync(()->{
            //sku优惠券
            Map<String,Object> activityMap=activityFeignClient.findActivityAndCoupon(id,userId);
            if(activityMap!=null)
            res.putAll(activityMap);

        },executor);
        CompletableFuture<Void> hotCompletableFuture = CompletableFuture.runAsync(()->{
            //skuhot
            searchFeignClient.incrHotScore(id);

        },executor);
        CompletableFuture.allOf(skuInfoVoCompletableFuture,
                activityompletable,
                hotCompletableFuture).join();

        return res;
    }
}
