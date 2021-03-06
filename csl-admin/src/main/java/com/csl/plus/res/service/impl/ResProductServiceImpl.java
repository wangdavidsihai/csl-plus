package com.csl.plus.res.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.audit.entity.ReviewLog;
import com.csl.plus.audit.mapper.ReviewLogMapper;
import com.csl.plus.common.utils.CommonCodes;
import com.csl.plus.res.entity.ResProduct;
import com.csl.plus.res.mapper.ResProductMapper;
import com.csl.plus.res.service.IResProductService;
import com.csl.plus.util.UserUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service("resProductService")
public class ResProductServiceImpl extends ServiceImpl<ResProductMapper, ResProduct> implements IResProductService {

    @Resource
    private ResProductMapper resProductMapper;
    @Resource
    private ReviewLogMapper reviewLogMapper;

    @Transactional
    public boolean saves(ResProduct entity) {
        entity.setCreateDate(new Date());
        resProductMapper.insert(entity);
        return true;
    }

    @Override
    public List<ResProduct> getList() {
        return resProductMapper.getList();
    }

    @Override
    public int updateVerifyStatus(Long ids, Integer verifyStatus, String detail) {
        ResProduct entity = new ResProduct();
        entity.setVerifyStatus(verifyStatus);
        int count = resProductMapper.update(entity, new QueryWrapper<ResProduct>().eq("id", ids));
        //修改完审核状态后插入审核记录

        ReviewLog record = new ReviewLog();
        record.setRefId(ids);
        record.setCreateDate(new Date());
        record.setDetail(detail);
        record.setStatus(verifyStatus);
        record.setApprover(UserUtils.getCurrentMember().getUsername());
        record.setSysGroup(CommonCodes.SYS_GROUP_RES_PROD);
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