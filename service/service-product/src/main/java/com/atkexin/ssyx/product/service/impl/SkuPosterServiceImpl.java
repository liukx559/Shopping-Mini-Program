package com.atkexin.ssyx.product.service.impl;

import com.atkexin.ssyx.model.product.SkuPoster;
import com.atkexin.ssyx.product.mapper.SkuPosterMapper;
import com.atkexin.ssyx.product.service.SkuPosterService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品海报表 服务实现类
 * </p>
 *
 * @author atkexin
 * @since 2024-03-06
 */
@Service
public class SkuPosterServiceImpl extends ServiceImpl<SkuPosterMapper, SkuPoster> implements SkuPosterService {

    @Override
    public List<SkuPoster> findBySkuId(Long skuId) {
        LambdaQueryWrapper<SkuPoster> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(SkuPoster::getSkuId,skuId);
        List<SkuPoster> skuPosters = baseMapper.selectList(wrapper);

        return skuPosters;
    }
}
