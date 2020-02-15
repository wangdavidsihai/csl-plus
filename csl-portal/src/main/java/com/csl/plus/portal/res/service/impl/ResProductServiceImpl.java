package com.csl.plus.portal.res.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.portal.res.service.IResProductService;
import com.csl.plus.res.entity.ResProduct;
import com.csl.plus.res.mapper.ResProductMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


@Service("resProductService")
public class ResProductServiceImpl extends ServiceImpl<ResProductMapper, ResProduct> implements IResProductService {

    @Resource
    private ResProductMapper resProductMapper;

    @Transactional
    public boolean saves(ResProduct entity) {
        entity.setCreateDate(new Date());
        resProductMapper.insert(entity);
        return true;
    }

}