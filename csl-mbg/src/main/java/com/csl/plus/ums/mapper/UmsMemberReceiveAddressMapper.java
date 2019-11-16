package com.csl.plus.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csl.plus.ums.entity.UmsMemberReceiveAddress;

/**
 * <p>
 * 会员收货地址表 Mapper 接口
 * </p>
 *
 *  
 * @since 2019-04-19
 */
public interface UmsMemberReceiveAddressMapper extends BaseMapper<UmsMemberReceiveAddress> {

    void updateStatusByMember(Long memberId);
}
