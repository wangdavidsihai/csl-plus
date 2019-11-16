package com.csl.plus.cms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.cms.entity.CmsTopic;

/**
 * <p>
 * 话题表 服务类
 * </p>
 *
 * @since 2019-04-17
 */
public interface ICmsTopicService extends IService<CmsTopic> {

    int updateVerifyStatus(Long ids, Long topicId,Integer verifyStatus);

    boolean saves(CmsTopic entity);
}
