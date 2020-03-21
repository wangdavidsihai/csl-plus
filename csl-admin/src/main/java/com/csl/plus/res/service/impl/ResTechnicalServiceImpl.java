package com.csl.plus.res.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.audit.entity.ReviewLog;
import com.csl.plus.audit.mapper.ReviewLogMapper;
import com.csl.plus.res.entity.ResTechnical;
import com.csl.plus.res.mapper.ResTechnicalMapper;
import com.csl.plus.res.service.IResTechnicalService;
import com.csl.plus.util.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service("resTechnicalService")
public class ResTechnicalServiceImpl extends ServiceImpl<ResTechnicalMapper, ResTechnical> implements IResTechnicalService {

    @Resource
    private ResTechnicalMapper resTechnicalMapper;
    @Resource
    private ReviewLogMapper reviewLogMapper;

    @Transactional
    public boolean saves(ResTechnical entity) {
        entity.setCreateDate(new Date());
        resTechnicalMapper.insert(entity);
        return true;
    }

    @Override
    public List<ResTechnical> getList() {
        return resTechnicalMapper.getList();
    }

    @Override
    public int updateVerifyStatus(Long ids, Integer verifyStatus, String detail) {
        ResTechnical entity = new ResTechnical();
        entity.setVerifyStatus(verifyStatus);
        int count = resTechnicalMapper.update(entity, new QueryWrapper<ResTechnical>().eq("id", ids));
        //修改完审核状态后插入审核记录

        ReviewLog record = new ReviewLog();
        record.setRefId(ids);
        record.setCreateDate(new Date());
        record.setDetail(detail);
        record.setStatus(verifyStatus);
        record.setApprover(UserUtils.getCurrentMember().getUsername());
        reviewLogMapper.insert(record);
        return count;
    }

}