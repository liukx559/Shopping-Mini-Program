package com.atkexin.ssyx.product.service.impl;

import com.atkexin.ssyx.model.product.SkuImage;
import com.atkexin.ssyx.product.mapper.SkuImageMapper;
import com.atkexin.ssyx.product.service.SkuImageService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkuImageServiceImpl extends ServiceImpl<SkuImageMapper,SkuImage> implements SkuImageService {
    @Override
    public List<SkuImage> findBySkuId(Long skuId) {
        LambdaQueryWrapper<SkuImage> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(SkuImage::getSkuId,skuId);
        List<SkuImage> skuImages = baseMapper.selectList(wrapper);
        return skuImages;
    }
}
