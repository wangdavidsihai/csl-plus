package com.csl.plus.portal.ums.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.portal.ums.service.IUmsMemberReceiveAddressService;
import com.csl.plus.portal.util.UserUtils;
import com.csl.plus.ums.entity.UmsMember;
import com.csl.plus.ums.entity.UmsMemberReceiveAddress;
import com.csl.plus.ums.mapper.UmsMemberReceiveAddressMapper;

/**
 * <p>
 * 会员收货地址表 服务实现类
 * </p>
 *
 * @since 2019-04-19
 */
@Service
public class UmsMemberReceiveAddressServiceImpl extends
		ServiceImpl<UmsMemberReceiveAddressMapper, UmsMemberReceiveAddress> implements IUmsMemberReceiveAddressService {

	@Resource
	private UmsMemberReceiveAddressMapper addressMapper;

	@Override
	public UmsMemberReceiveAddress getDefaultItem() {

		UmsMember currentMember = UserUtils.getCurrentMember();
		UmsMemberReceiveAddress q = new UmsMemberReceiveAddress();
		q.setDefaultStatus(1);
		q.setMemberId(currentMember.getId());
		return this.getOne(new QueryWrapper<>(q));
	}

	@Transactional
	@Override
	public int setDefault(Long id) {
		UmsMember currentMember = UserUtils.getCurrentMember();
		addressMapper.updateStatusByMember(currentMember.getId());

		UmsMemberReceiveAddress def = new UmsMemberReceiveAddress();
		def.setId(id);
		def.setDefaultStatus(1);
		this.updateById(def);
		return 1;
	}
}
