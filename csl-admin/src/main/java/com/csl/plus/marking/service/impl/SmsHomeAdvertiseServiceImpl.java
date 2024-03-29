package com.csl.plus.marking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.marking.entity.SmsHomeAdvertise;
import com.csl.plus.marking.entity.SmsHomeNewProduct;
import com.csl.plus.marking.mapper.SmsHomeAdvertiseMapper;
import com.csl.plus.marking.mapper.SmsHomeNewProductMapper;
import com.csl.plus.marking.service.ISmsHomeAdvertiseService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 首页轮播广告表 服务实现类
 * </p>
 *
 * @since 2019-04-19
 */
@Service
public class SmsHomeAdvertiseServiceImpl extends ServiceImpl<SmsHomeAdvertiseMapper, SmsHomeAdvertise> implements ISmsHomeAdvertiseService {
    @Resource
    private SmsHomeNewProductMapper homeNewProductMapper;
    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeNewProduct homeNewProduct = new SmsHomeNewProduct();
        homeNewProduct.setId(id);
        homeNewProduct.setSort(sort);
        return homeNewProductMapper.updateById(homeNewProduct);
    }
    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        SmsHomeNewProduct record = new SmsHomeNewProduct();
        record.setRecommendStatus(recommendStatus);
        return homeNewProductMapper.update(record, new QueryWrapper<SmsHomeNewProduct>().in("id",ids));
    }
}
