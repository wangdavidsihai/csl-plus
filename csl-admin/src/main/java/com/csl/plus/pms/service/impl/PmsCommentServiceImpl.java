package com.csl.plus.pms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.pms.entity.PmsComment;
import com.csl.plus.pms.mapper.PmsCommentMapper;
import com.csl.plus.pms.service.IPmsCommentService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品评价表 服务实现类
 * </p>
 *
 * @since 2019-04-19
 */
@Service
public class PmsCommentServiceImpl extends ServiceImpl<PmsCommentMapper, PmsComment> implements IPmsCommentService {

}
