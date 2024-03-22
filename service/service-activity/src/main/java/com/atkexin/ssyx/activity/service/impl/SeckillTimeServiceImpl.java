package com.atkexin.ssyx.activity.service.impl;


import com.atkexin.ssyx.activity.mapper.SeckillTimeMapper;
import com.atkexin.ssyx.activity.service.SeckillTimeService;
import com.atkexin.ssyx.model.activity.SeckillTime;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 秒杀活动场次 服务实现类
 * </p>
 *
 * @author atkexin
 * @since 2024-03-14
 */
@Service
public class SeckillTimeServiceImpl extends ServiceImpl<SeckillTimeMapper, SeckillTime> implements SeckillTimeService {

    @Override
    public void updateStatus(Long id, Integer status) {

    }
}
