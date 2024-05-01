
package com.atkexin.ssyx.search.service;

import com.atkexin.ssyx.model.search.SkuEs;
import com.atkexin.ssyx.vo.search.SkuEsQueryVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SkuService {

    /**
     * 上架商品列表
     * @param skuId
     */
    void upperSku(Long skuId);

    /**
     * 下架商品列表
     * @param skuId
     */
    void lowerSku(Long skuId);

    Page<SkuEs> search(Pageable pageable, SkuEsQueryVo searchParamVo);

    List<SkuEs> findHotSkuList();

    void incrHotScore(long skuId);
}


