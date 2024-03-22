package com.atkexin.ssyx.activity.service.impl;


import com.atkexin.ssyx.activity.mapper.SeckillSkuMapper;
import com.atkexin.ssyx.activity.service.SeckillSkuService;
import com.atkexin.ssyx.model.activity.SeckillSku;
import com.atkexin.ssyx.vo.activity.SeckillSkuQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 秒杀活动商品关联 服务实现类
 * </p>
 *
 * @author atkexin
 * @since 2024-03-14
 */
@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class SeckillSkuServiceImpl extends ServiceImpl<SeckillSkuMapper, SeckillSku> implements SeckillSkuService {


    @Override
    public IPage<SeckillSku> selectPage(Page<SeckillSku> pageParam, SeckillSkuQueryVo seckillSkuQueryVo) {
        return null;
    }
}