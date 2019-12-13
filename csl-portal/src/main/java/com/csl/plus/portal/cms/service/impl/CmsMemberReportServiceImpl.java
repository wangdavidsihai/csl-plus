package com.csl.plus.portal.cms.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.cms.entity.CmsMemberReport;
import com.csl.plus.cms.mapper.CmsMemberReportMapper;
import com.csl.plus.portal.cms.service.ICmsMemberReportService;

/**
 * <p>
 * 用户举报表 服务实现类
 * </p>
 *
 * @since 2019-04-17
 */
@Service
public class CmsMemberReportServiceImpl extends ServiceImpl<CmsMemberReportMapper, CmsMemberReport>
		implements ICmsMemberReportService {

}
