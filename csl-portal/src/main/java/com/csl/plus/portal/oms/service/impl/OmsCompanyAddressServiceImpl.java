package com.csl.plus.portal.oms.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.oms.entity.OmsCompanyAddress;
import com.csl.plus.oms.mapper.OmsCompanyAddressMapper;
import com.csl.plus.portal.oms.service.IOmsCompanyAddressService;

/**
 * <p>
 * 公司收发货地址表 服务实现类
 * </p>
 *
 * @since 2019-04-17
 */
@Service
public class OmsCompanyAddressServiceImpl extends ServiceImpl<OmsCompanyAddressMapper, OmsCompanyAddress>
		implements IOmsCompanyAddressService {

}
