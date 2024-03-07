package com.atkexin.ssyx.product.service.impl;

import com.atkexin.ssyx.model.product.SkuAttrValue;
import com.atkexin.ssyx.product.mapper.SkuAttrValueMapper;
import com.atkexin.ssyx.product.service.SkuAttrValueService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkuAttrValueServiceImpl extends ServiceImpl<SkuAttrValueMapper, SkuAttrValue> implements SkuAttrValueService {
    @Override
    public List<SkuAttrValue> findBySkuId(Long skuId) {
        LambdaQueryWrapper<SkuAttrValue> wrapper =new LambdaQueryWrapper<>();
        wrapper.eq(SkuAttrValue::getSkuId, skuId);
        List<SkuAttrValue> skuAttrValues = baseMapper.selectList(wrapper);
        return skuAttrValues;
    }
}
