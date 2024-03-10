package com.atkexin.ssyx.product.service.impl;

import com.atkexin.ssyx.model.product.Comment;
import com.atkexin.ssyx.product.mapper.CommentMapper;
import com.atkexin.ssyx.product.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品评价 服务实现类
 * </p>
 *
 * @author atkexin
 * @since 2024-03-06
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
