package com.csl.plus.portal.ums.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.portal.ums.service.IUmsIntegrationChangeHistoryService;
import com.csl.plus.ums.entity.UmsIntegrationChangeHistory;
import com.csl.plus.ums.mapper.UmsIntegrationChangeHistoryMapper;

/**
 * <p>
 * 积分变化历史记录表 服务实现类
 * </p>
 *
 * @since 2019-04-19
 */
@Service
public class UmsIntegrationChangeHistoryServiceImpl
		extends ServiceImpl<UmsIntegrationChangeHistoryMapper, UmsIntegrationChangeHistory>
		implements IUmsIntegrationChangeHistoryService {

}
