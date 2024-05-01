package com.atkexin.ssyx.activity.client;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
@Component
@FeignClient(name = "service-activity")
public interface ActivityFeignClient {
    @PostMapping("/api/activity/inner/findActivity")
    public Map<Long, List<String>> findActivity(@RequestBody List<Long> skuIdList) ;

    @GetMapping("/api/activity/inner/findActivityAndCoupon/{skuId}{userId}")
    public Map<String, Object> findActivityAndCoupon(@PathVariable long skuId, @PathVariable long userId) ;

}
