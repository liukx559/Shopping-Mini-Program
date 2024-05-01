package com.atkexin.ssyx.cart.service.impl;

import com.atkexin.ssyx.cart.service.CartInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartInfoServicelmpi implements CartInfoService {

    @Override
    public void addToCart(Long skuId, Long userId, Integer skuNum) {

    }

    @Override
    public void deleteCart(Long skuId, Long userId) {

    }

    @Override
    public void deleteAllCart(Long userId) {

    }

    @Override
    public void batchDeleteCart(List<Long> skuIdList, Long userId) {

    }
}
