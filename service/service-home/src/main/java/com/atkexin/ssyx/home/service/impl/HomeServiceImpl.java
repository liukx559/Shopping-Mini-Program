package com.atkexin.ssyx.home.service.impl;

import com.atkexin.ssyx.client.user.UserFeignClient;
import com.atkexin.ssyx.home.service.HomeService;
import com.atkexin.ssyx.model.product.Category;
import com.atkexin.ssyx.model.product.SkuInfo;
import com.atkexin.ssyx.product.client.ProductFeignClient;
import com.atkexin.ssyx.vo.user.LeaderAddressVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private ProductFeignClient productFeignClient;


    @Override
    public Map<String, Object> home(Long userId) {
        Map<String, Object> result=new HashMap<>();
        //获取提货点user
        LeaderAddressVo userAddressByUserId = userFeignClient.getUserAddressByUserId(userId);
        result.put("LeaderAddressVo",userAddressByUserId);
        //获取分类product
        List<Category> categoryList = productFeignClient.findAllCategoryList();
        result.put("categoryList",categoryList);
        //新人商品product
        List<SkuInfo> newPersonList=productFeignClient.findNewPersonSkuInfoList();
        result.put("newPersonList",newPersonList);
        //热搜商品es
        //评分降序
        

        return null;
    }
}
