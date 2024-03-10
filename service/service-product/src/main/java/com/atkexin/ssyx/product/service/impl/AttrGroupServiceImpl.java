package com.atkexin.ssyx.product.service.impl;
import com.atkexin.ssyx.model.product.AttrGroup;
import com.atkexin.ssyx.product.mapper.AttrGroupMapper;
import com.atkexin.ssyx.product.service.AttrGroupService;
import com.atkexin.ssyx.vo.product.AttrGroupQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupMapper, AttrGroup> implements AttrGroupService {

    //平台属性分组列表
    @Override
    public IPage<AttrGroup> selectPage(Page<AttrGroup> pageParam, AttrGroupQueryVo attrGroupQueryVo) {
        String name = attrGroupQueryVo.getName();
        LambdaQueryWrapper<AttrGroup> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            wrapper.like(AttrGroup::getName,name);
        }
        IPage<AttrGroup> attrGroupPage = baseMapper.selectPage(pageParam, wrapper);
        return attrGroupPage;
    }

    //查询所有属性分组
    @Override
    public List<AttrGroup> findAllList() {
        return this.list();
    }
}
