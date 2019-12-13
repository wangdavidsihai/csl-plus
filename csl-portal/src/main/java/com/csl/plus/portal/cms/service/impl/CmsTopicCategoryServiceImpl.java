package com.csl.plus.portal.cms.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.cms.entity.CmsTopicCategory;
import com.csl.plus.cms.mapper.CmsTopicCategoryMapper;
import com.csl.plus.portal.cms.service.ICmsTopicCategoryService;

/**
 * <p>
 * 话题分类表 服务实现类
 * </p>
 *
 * @since 2019-04-17
 */
@Service
public class CmsTopicCategoryServiceImpl extends ServiceImpl<CmsTopicCategoryMapper, CmsTopicCategory>
		implements ICmsTopicCategoryService {

}
