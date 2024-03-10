package com.atkexin.ssyx.product.service;

import com.atkexin.ssyx.model.product.AttrGroup;
import com.atkexin.ssyx.vo.product.AttrGroupQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 属性分组 服务类
 * </p>
 *
 * @author atkexin
 * @since 2024-03-06
 */
public interface AttrGroupService extends IService<AttrGroup> {

    IPage<AttrGroup> selectPage(Page<AttrGroup> pageParam, AttrGroupQueryVo attrGroupQueryVo);

    Object findAllList();
}
