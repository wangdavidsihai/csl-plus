package com.csl.plus.res.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.audit.entity.ReviewLog;
import com.csl.plus.audit.mapper.ReviewLogMapper;
import com.csl.plus.common.utils.CommonCodes;
import com.csl.plus.res.entity.ResExpert;
import com.csl.plus.res.mapper.ResExpertMapper;
import com.csl.plus.res.service.IResExpertService;
import com.csl.plus.util.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service("resExpertService")
public class ResExpertServiceImpl extends ServiceImpl<ResExpertMapper, ResExpert> implements IResExpertService {

    @Resource
    private ResExpertMapper resExpertMapper;
    @Resource
    private ReviewLogMapper reviewLogMapper;

    @Transactional
    public boolean saves(ResExpert entity) {
        entity.setCreateDate(new Date());
        resExpertMapper.insert(entity);
        return true;
    }

    @Override
    public int updateVerifyStatus(Long ids, Integer verifyStatus, String detail) {
        ResExpert entity = new ResExpert();
        entity.setVerifyStatus(verifyStatus);
        int count = resExpertMapper.update(entity, new QueryWrapper<ResExpert>().eq("id", ids));
        //修改完审核状态后插入审核记录

        ReviewLog record = new ReviewLog();
        record.setRefId(ids);
        record.setCreateDate(new Date());
        record.setDetail(detail);
        record.setStatus(verifyStatus);
        record.setApprover(UserUtils.getCurrentMember().getUsername());
        record.setSysGroup(CommonCodes.SYS_GROUP_RES_TECH);
        reviewLogMapper.insert(record);
        return count;
    }

    @Override
    public List<ReviewLog> getVertifyRecord(Long id, String sysGroup) {
        ReviewLog reviewLog = new ReviewLog();
        reviewLog.setSysGroup(sysGroup);
        reviewLog.setRefId(id);
        return reviewLogMapper.selectList(new QueryWrapper<>(reviewLog));
    }

}