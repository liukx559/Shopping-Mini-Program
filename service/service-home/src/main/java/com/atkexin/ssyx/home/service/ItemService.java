package com.atkexin.ssyx.home.service;

import com.google.protobuf.Service;

import java.util.Map;

public interface ItemService  {
    Map<String,Object> item(Long id, Long userId);
}
