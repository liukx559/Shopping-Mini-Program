package com.atkexin.ssyx.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.atkexin.ssyx.client.product.ProductFeignClient;
import com.atkexin.ssyx.enums.SkuType;
import com.atkexin.ssyx.model.product.Category;
import com.atkexin.ssyx.model.product.SkuInfo;
import com.atkexin.ssyx.model.search.SkuEs;
import com.atkexin.ssyx.repository.SkuRepository;
import com.atkexin.ssyx.search.service.SkuService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SkuServiceImpl implements SkuService {


    @Autowired
    private ProductFeignClient productFeignClient;

    @Autowired
    private SkuRepository skuEsRepository;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 上架商品列表
     *
     * @param skuId
     */
    @Override
    public void upperSku(Long skuId) {
        log.info("upperSku：" + skuId);
        SkuEs skuEs = new SkuEs();

        //查询sku信息
        SkuInfo skuInfo = productFeignClient.getSkuInfo(skuId);
        if (null == skuInfo) return;

        // 查询分类
        Category category = productFeignClient.getCategory(skuInfo.getCategoryId());
        //整合成Es
        if (category != null) {
            skuEs.setCategoryId(category.getId());
            skuEs.setCategoryName(category.getName());
        }

        skuEs.setId(skuInfo.getId());
        skuEs.setKeyword(skuInfo.getSkuName() + "," + skuEs.getCategoryName());
        skuEs.setWareId(skuInfo.getWareId());
        skuEs.setIsNewPerson(skuInfo.getIsNewPerson());
        skuEs.setImgUrl(skuInfo.getImgUrl());
        skuEs.setTitle(skuInfo.getSkuName());
        if (skuInfo.getSkuType() == SkuType.COMMON.getCode()) {//普通商品
            skuEs.setSkuType(0);
            skuEs.setPrice(skuInfo.getPrice().doubleValue());
            skuEs.setStock(skuInfo.getStock());
            skuEs.setSale(skuInfo.getSale());
            skuEs.setPerLimit(skuInfo.getPerLimit());
        } else {
            //TODO 待完善-秒杀商品

        }
        //Es区别于MySQL
        SkuEs save = skuEsRepository.save(skuEs);
        log.info("upperSku：" + JSON.toJSONString(save));
    }

    /**
     * 下架商品列表
     *
     * @param skuId
     */
    @Override
    public void lowerSku(Long skuId) {
        this.skuEsRepository.deleteById(skuId);
    }
}