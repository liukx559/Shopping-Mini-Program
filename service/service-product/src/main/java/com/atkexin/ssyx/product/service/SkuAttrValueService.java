package com.atkexin.ssyx.product.service;

import com.atkexin.ssyx.model.product.SkuAttrValue;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SkuAttrValueService extends IService<SkuAttrValue> {
    List<SkuAttrValue> findBySkuId(Long skuId);
}
