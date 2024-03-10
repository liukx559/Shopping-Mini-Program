package com.atkexin.ssyx.product.service;

import com.atkexin.ssyx.model.product.SkuPoster;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 商品海报表 服务类
 * </p>
 *
 * @author atkexin
 * @since 2024-03-06
 */
public interface SkuPosterService extends IService<SkuPoster> {

    List<SkuPoster> findBySkuId(Long skuId);
}
