package com.csl.plus.portal.inbox.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.inbox.entity.MtMessageServ;
import com.csl.plus.inbox.mapper.MtMessageServMapper;
import com.csl.plus.portal.inbox.service.IMtMessageServService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


@Service("mtMessageServService")
public class MtMessageServServiceImpl extends ServiceImpl<MtMessageServMapper, MtMessageServ> implements IMtMessageServService {

    @Resource
    private MtMessageServMapper mtMessageServMapper;
    
    @Transactional
    public boolean saves(MtMessageServ entity){
    	entity.setSendDate(new Date());
        mtMessageServMapper.insert(entity);
        return true;
    }

}