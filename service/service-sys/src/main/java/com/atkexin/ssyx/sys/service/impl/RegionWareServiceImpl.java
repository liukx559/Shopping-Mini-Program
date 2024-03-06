package com.atkexin.ssyx.sys.service.impl;

import com.atkexin.ssyx.common.exception.SsyxException;
import com.atkexin.ssyx.common.result.ResultCodeEnum;
import com.atkexin.ssyx.model.sys.RegionWare;
import com.atkexin.ssyx.sys.mapper.RegionWareMapper;
import com.atkexin.ssyx.sys.service.RegionWareService;
import com.atkexin.ssyx.vo.sys.RegionWareQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * <p>
 * 城市仓库关联表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2024-03-05
 */
@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class RegionWareServiceImpl extends ServiceImpl<RegionWareMapper, RegionWare> implements RegionWareService {

    @Resource
    private RegionWareMapper regionWareMapper;

    //开通区域列表
    @Override
    public IPage<RegionWare> selectPage(Page<RegionWare> pageParam, RegionWareQueryVo regionWareQueryVo) {
        String keyword = regionWareQueryVo.getKeyword();
        LambdaQueryWrapper<RegionWare> wrapper = new LambdaQueryWrapper<>();
        if(!StringUtils.isEmpty(keyword)) {
            wrapper.like(RegionWare::getRegionName,keyword)
                    .or().like(RegionWare::getWareName,keyword);
        }

        IPage<RegionWare> regionWarePage = baseMapper.selectPage(pageParam, wrapper);
        return regionWarePage;
    }

    @Override
    public void saveRegionWare(RegionWare regionWare) {
        LambdaQueryWrapper<RegionWare> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(RegionWare::getRegionId, regionWare.getRegionId());
        Integer count = regionWareMapper.selectCount(queryWrapper);
        if(count > 0) {
            throw new SsyxException(ResultCodeEnum.REGION_OPEN);
        }
        baseMapper.insert(regionWare);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        RegionWare regionWare = baseMapper.selectById(id);
        regionWare.setStatus(status);
        baseMapper.updateById(regionWare);
    }
}
