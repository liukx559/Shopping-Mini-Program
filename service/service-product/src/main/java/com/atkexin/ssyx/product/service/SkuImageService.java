package com.atkexin.ssyx.product.service;

import com.atkexin.ssyx.model.product.SkuImage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SkuImageService extends IService<SkuImage> {
    List<SkuImage> findBySkuId(Long skuId);
}
