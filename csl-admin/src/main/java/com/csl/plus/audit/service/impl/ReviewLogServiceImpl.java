package com.csl.plus.audit.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.csl.plus.audit.mapper.ReviewLogMapper;
import com.csl.plus.audit.entity.ReviewLog;
import com.csl.plus.audit.service.IReviewLogService;

import javax.annotation.Resource;
import java.util.Date;


@Service("reviewLogService")
public class ReviewLogServiceImpl extends ServiceImpl<ReviewLogMapper, ReviewLog> implements IReviewLogService {

    @Resource
    private ReviewLogMapper reviewLogMapper;
    
    @Transactional
    public boolean saves(ReviewLog entity){
    	entity.setCreateDate(new Date());
        reviewLogMapper.insert(entity);
        return true;
    }

}