package com.atkexin.ssyx.sys.service;

import com.atkexin.ssyx.model.sys.RegionWare;
import com.atkexin.ssyx.vo.sys.RegionWareQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 城市仓库关联表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2024-03-05
 */
public interface RegionWareService extends IService<RegionWare> {
    IPage<RegionWare> selectPage(Page<RegionWare> pageParam,
                                 RegionWareQueryVo regionWareQueryVo);

    void saveRegionWare(RegionWare regionWare);

    void updateStatus(Long id, Integer status);
}
