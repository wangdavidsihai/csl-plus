package com.csl.plus.marking.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.marking.entity.SmsHomeBrand;
import com.csl.plus.marking.mapper.SmsHomeBrandMapper;
import com.csl.plus.marking.service.ISmsHomeBrandService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 首页推荐品牌表 服务实现类
 * </p>
 *
 * @since 2019-04-19
 */
@Service
public class SmsHomeBrandServiceImpl extends ServiceImpl<SmsHomeBrandMapper, SmsHomeBrand> implements ISmsHomeBrandService {

    @Resource
    private SmsHomeBrandMapper homeBrandMapper;

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeBrand homeBrand = new SmsHomeBrand();
        homeBrand.setId(id);
        homeBrand.setSort(sort);
        return homeBrandMapper.updateById(homeBrand);
    }
    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        SmsHomeBrand record = new SmsHomeBrand();
        record.setRecommendStatus(recommendStatus);
        return homeBrandMapper.update(record, new QueryWrapper<SmsHomeBrand>().in("id",ids));
    }
}
