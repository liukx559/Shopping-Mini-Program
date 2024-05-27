package com.atkexin.ssyx.cart.service;

import com.atkexin.ssyx.model.order.CartInfo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CartInfoService {
    void addToCart(Long skuId, Long userId, Integer skuNum) ;

    void deleteCart(Long skuId, Long userId);

    void deleteAllCart(Long userId);

    void batchDeleteCart(List<Long> skuIdList, Long userId);

    List<CartInfo> getCartList(Long userId);
}
