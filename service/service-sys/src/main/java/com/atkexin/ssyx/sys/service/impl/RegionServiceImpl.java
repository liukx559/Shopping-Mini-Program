package com.atkexin.ssyx.sys.service.impl;

import com.atkexin.ssyx.model.sys.Region;
import com.atkexin.ssyx.sys.mapper.RegionMapper;
import com.atkexin.ssyx.sys.service.RegionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 地区表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2024-03-05
 */
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements RegionService {

    @Override
    public Object findRegionByKeyword(String keyword) {
        LambdaQueryWrapper<Region> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Region::getName, keyword);
        return baseMapper.selectList(queryWrapper);
    }
}
