package com.atkexin.ssyx.product.service;

import com.atkexin.ssyx.model.product.Category;
import com.atkexin.ssyx.vo.product.CategoryQueryVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 商品三级分类 服务类
 * </p>
 *
 * @author atkexin
 * @since 2024-03-06
 */
public interface CategoryService extends IService<Category> {

    IPage<com.atkexin.ssyx.model.product.Category> selectPage(Page<Category> pageParam, CategoryQueryVo categoryQueryVo);

    Object findAllList();



    ;
}
