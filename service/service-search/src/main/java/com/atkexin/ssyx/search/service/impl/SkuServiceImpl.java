package com.atkexin.ssyx.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.utils.StringUtils;
import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.atkexin.ssyx.activity.client.ActivityFeignClient;
import com.atkexin.ssyx.common.auth.AuthContextHolder;
import com.atkexin.ssyx.enums.SkuType;
import com.atkexin.ssyx.model.product.Category;
import com.atkexin.ssyx.model.product.SkuInfo;
import com.atkexin.ssyx.model.search.SkuEs;
import com.atkexin.ssyx.product.client.ProductFeignClient;
import com.atkexin.ssyx.search.mapper.SkuRepository;
import com.atkexin.ssyx.search.service.SkuService;
import com.atkexin.ssyx.vo.search.SkuEsQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
public class SkuServiceImpl implements SkuService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Autowired
    private ProductFeignClient productFeignClient;

    @Autowired
    private SkuRepository skuRepository;

    @Autowired
    private ActivityFeignClient activityFeignClient;



    //获取爆品商品
    @Override
    public List<SkuEs> findHotSkuList() {
        Pageable pageable = (Pageable) PageRequest.of(0, 10);
        return skuRepository.findByOrderByHotScoreDesc(pageable).getContent();
    }
    @Autowired
    private RedisTemplate redisTemplate;
    //更新商品热度、更新es的值
    @Override
    public void incrHotScore(long skuId) {
        //1、商品被查看一次，更新一次es，es存磁盘。IO操作。es搜索快、更新不快
        //2、redis实现，在redis中更新
        //3、规则redis中的hotsorce达到阈值时才更新es
        //4、redis类型：String，Zset(有序，不能重复)
        String key="hotScore";
        Double v = redisTemplate.opsForZSet().incrementScore(key, "skuId" + skuId, 1);
        if(v%10==0){
            Optional<SkuEs> byId = skuRepository.findById(skuId);
            SkuEs skuEs=byId.get();
            skuEs.setHotScore(Math.round(v));
            skuRepository.save(skuEs);
        }


    }

    /**
     * 上架商品列表
     * @param skuId
     */
    @Override
    public void upperSku(Long skuId) {
        log.info("upperSku："+skuId);
        SkuEs skuEs = new SkuEs();

        //查询sku信息
        SkuInfo skuInfo = productFeignClient.getSkuInfo(skuId);
        if(null == skuInfo) return;

        // 查询分类
        Category category = productFeignClient.getCategory(skuInfo.getCategoryId());
        if (category != null) {
            skuEs.setCategoryId(category.getId());
            skuEs.setCategoryName(category.getName());
        }
        skuEs.setId(skuInfo.getId());
        skuEs.setKeyword(skuInfo.getSkuName()+","+skuEs.getCategoryName());
        skuEs.setWareId(skuInfo.getWareId());
        skuEs.setIsNewPerson(skuInfo.getIsNewPerson());
        skuEs.setImgUrl(skuInfo.getImgUrl());
        skuEs.setTitle(skuInfo.getSkuName());
        if(skuInfo.getSkuType() == SkuType.COMMON.getCode()) {
            skuEs.setSkuType(0);
            skuEs.setPrice(skuInfo.getPrice().doubleValue());
            skuEs.setStock(skuInfo.getStock());
            skuEs.setSale(skuInfo.getSale());
            skuEs.setPerLimit(skuInfo.getPerLimit());
        } else {
            //TODO 待完善-秒杀商品

        }
        SkuEs save = skuRepository.save(skuEs);
        log.info("upperSku："+JSON.toJSONString(save));
    }

    /**
     * 下架商品列表
     * @param skuId
     */
    @Override
    public void lowerSku(Long skuId) {
        this.skuRepository.deleteById(skuId);
    }

    @Override
    public Page<SkuEs> search(Pageable pageable, SkuEsQueryVo skuEsQueryVo) {
        skuEsQueryVo.setWareId(AuthContextHolder.getWareId());
        Page<SkuEs> page = null;
        //查ES：skuRepository，而不是service、mapper
        if(StringUtils.isEmpty(skuEsQueryVo.getKeyword())) {
            page = skuRepository.findByCategoryIdAndWareId(skuEsQueryVo.getCategoryId(), skuEsQueryVo.getWareId(), pageable);
        } else {
            page = skuRepository.findByKeywordAndWareId(skuEsQueryVo.getKeyword(), skuEsQueryVo.getWareId(), pageable);
        }

        List<SkuEs>  skuEsList =  page.getContent();
        //获取sku对应的促销活动标签
        if(!CollectionUtils.isEmpty(skuEsList)) {
            List<Long> skuIdList = skuEsList.stream().map(sku -> sku.getId()).collect(Collectors.toList());
            //key:skuId,value:rulelist
            Map<Long, List<String>> skuIdToRuleListMap = activityFeignClient.findActivity(skuIdList);
            if(null != skuIdToRuleListMap) {
                skuEsList.forEach(skuEs -> {
                    skuEs.setRuleList(skuIdToRuleListMap.get(skuEs.getId()));
                });
            }
        }
        return page;
    }
}