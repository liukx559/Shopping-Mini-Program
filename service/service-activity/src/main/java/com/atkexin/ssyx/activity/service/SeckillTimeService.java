package com.atkexin.ssyx.activity.service;


import com.atkexin.ssyx.model.activity.SeckillTime;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 秒杀活动场次 服务类
 * </p>
 *
 * @author atkexin
 * @since 2024-03-14
 */
public interface SeckillTimeService extends IService<SeckillTime> {

    void updateStatus(Long id, Integer status);
}
