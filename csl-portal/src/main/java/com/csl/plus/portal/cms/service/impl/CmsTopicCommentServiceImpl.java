package com.csl.plus.portal.cms.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.cms.entity.CmsTopicComment;
import com.csl.plus.cms.mapper.CmsTopicCommentMapper;
import com.csl.plus.portal.cms.service.ICmsTopicCommentService;

/**
 * <p>
 * 专题评论表 服务实现类
 * </p>
 *
 * @since 2019-04-17
 */
@Service
public class CmsTopicCommentServiceImpl extends ServiceImpl<CmsTopicCommentMapper, CmsTopicComment>
		implements ICmsTopicCommentService {

}
