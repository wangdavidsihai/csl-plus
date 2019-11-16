package com.csl.plus.oms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.oms.entity.OmsCartItem;
import com.csl.plus.oms.mapper.OmsCartItemMapper;
import com.csl.plus.oms.service.IOmsCartItemService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 购物车表 服务实现类
 * </p>
 *
 * @since 2019-04-17
 */
@Service
public class OmsCartItemServiceImpl extends ServiceImpl<OmsCartItemMapper, OmsCartItem> implements IOmsCartItemService {

}
