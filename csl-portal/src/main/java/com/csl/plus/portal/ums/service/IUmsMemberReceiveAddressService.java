package com.csl.plus.portal.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.ums.entity.UmsMemberReceiveAddress;

/**
 * <p>
 * 会员收货地址表 服务类
 * </p>
 *
 * @since 2019-04-19
 */
public interface IUmsMemberReceiveAddressService extends IService<UmsMemberReceiveAddress> {

	UmsMemberReceiveAddress getDefaultItem();

	int setDefault(Long id);
}
