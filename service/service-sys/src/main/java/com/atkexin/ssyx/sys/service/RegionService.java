package com.atkexin.ssyx.sys.service;

import com.atkexin.ssyx.model.sys.Region;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 地区表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2024-03-05
 */
public interface RegionService extends IService<Region> {

    Object findRegionByKeyword(String keyword);
}
