package com.atkexin.ssyx.home.service;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

public interface HomeService  {
    Map<String,Object> home(Long userId);
}
