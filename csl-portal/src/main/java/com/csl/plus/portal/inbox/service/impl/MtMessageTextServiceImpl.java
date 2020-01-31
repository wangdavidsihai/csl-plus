package com.csl.plus.portal.inbox.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.inbox.entity.MtMessageText;
import com.csl.plus.inbox.mapper.MtMessageTextMapper;
import com.csl.plus.portal.inbox.service.IMtMessageTextService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


@Service("mtMessageTextService")
public class MtMessageTextServiceImpl extends ServiceImpl<MtMessageTextMapper, MtMessageText> implements IMtMessageTextService {

    @Resource
    private MtMessageTextMapper mtMessageTextMapper;
    
    @Transactional
    public boolean saves(MtMessageText entity){
    	entity.setCreateDate(new Date());
        mtMessageTextMapper.insert(entity);
        return true;
    }

}