package com.csl.plus.res.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.audit.entity.ReviewLog;
import com.csl.plus.audit.mapper.ReviewLogMapper;
import com.csl.plus.common.utils.CommonCodes;
import com.csl.plus.res.entity.ResProject;
import com.csl.plus.res.mapper.ResProjectMapper;
import com.csl.plus.res.service.IResProjectService;
import com.csl.plus.util.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service("resProjProductionService")
public class ResProjectServiceImpl extends ServiceImpl<ResProjectMapper, ResProject> implements IResProjectService {

    @Resource
    private ResProjectMapper resProjectMapper;
    @Resource
    private ReviewLogMapper reviewLogMapper;

    @Transactional
    public boolean saves(ResProject entity) {
        entity.setCreateDate(new Date());
        resProjectMapper.insert(entity);
        return true;
    }

    @Override
    public List<ResProject> getList() {

        return resProjectMapper.getList();
    }

    @Override
    public int updateVerifyStatus(Long ids, Integer verifyStatus, String detail) {
        ResProject entity = new ResProject();
        entity.setVerifyStatus(verifyStatus);
        int count = resProjectMapper.update(entity, new QueryWrapper<ResProject>().eq("id", ids));
        //修改完审核状态后插入审核记录

        ReviewLog record = new ReviewLog();
        record.setRefId(ids);
        record.setCreateDate(new Date());
        record.setDetail(detail);
        record.setStatus(verifyStatus);
        record.setApprover(UserUtils.getCurrentMember().getUsername());
        record.setSysGroup(CommonCodes.SYS_GROUP_RES_PROJ);
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