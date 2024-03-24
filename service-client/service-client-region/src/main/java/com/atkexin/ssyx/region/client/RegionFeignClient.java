package com.atkexin.ssyx.region.client;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name = "service-sys")
public interface RegionFeignClient {

    @ApiOperation(value = "根据id查仓库")
    @GetMapping("/admin/sys/regionWare/get/{id}")
    Long getWareId(Long regionId);

}
