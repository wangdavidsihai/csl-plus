package com.csl.plus.oms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.oms.entity.OmsOrderSetting;
import com.csl.plus.oms.mapper.OmsOrderSettingMapper;
import com.csl.plus.oms.service.IOmsOrderSettingService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单设置表 服务实现类
 * </p>
 *
 * @since 2019-04-17
 */
@Service
public class OmsOrderSettingServiceImpl extends ServiceImpl<OmsOrderSettingMapper, OmsOrderSetting> implements IOmsOrderSettingService {

}
