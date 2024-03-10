package com.atkexin.ssyx.product.service;

import com.atkexin.ssyx.model.product.Attr;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品属性 服务类
 * </p>
 *
 * @author atkexin
 * @since 2024-03-06
 */
public interface AttrService extends IService<Attr> {

    Object findByAttrGroupId(Long attrGroupId);
}
