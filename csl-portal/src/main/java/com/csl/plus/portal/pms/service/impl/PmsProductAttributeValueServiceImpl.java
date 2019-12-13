package com.csl.plus.portal.pms.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.pms.entity.PmsProductAttributeValue;
import com.csl.plus.pms.mapper.PmsProductAttributeValueMapper;
import com.csl.plus.portal.pms.service.IPmsProductAttributeValueService;

/**
 * <p>
 * 存储产品参数信息的表 服务实现类
 * </p>
 *
 * @since 2019-04-19
 */
@Service
public class PmsProductAttributeValueServiceImpl
		extends ServiceImpl<PmsProductAttributeValueMapper, PmsProductAttributeValue>
		implements IPmsProductAttributeValueService {

}
