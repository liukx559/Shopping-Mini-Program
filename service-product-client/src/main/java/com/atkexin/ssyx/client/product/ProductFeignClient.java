package com.atkexin.ssyx.client.product;

import com.atkexin.ssyx.model.product.Category;
import com.atkexin.ssyx.model.product.SkuInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-product")//调用的微服务名字
public interface ProductFeignClient {

    @GetMapping("/api/product/inner/getCategory/{categoryId}")//类上的路径和接口路径
    public Category getCategory(@PathVariable("categoryId") Long categoryId);//service-product中api定义的控制类ProductInnnerController

    @GetMapping("/api/product/inner/getSkuInfo/{skuId}")
    public SkuInfo getSkuInfo(@PathVariable("skuId") Long skuId);
}