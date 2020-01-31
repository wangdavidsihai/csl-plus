package com.csl.plus.inbox.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.inbox.entity.MtMessageText;


/**
 * 消息内容表
 *
 * @author David
 * @email 
 * @date 2020-01-31 17:46:11
 */
public interface IMtMessageTextService extends IService<MtMessageText> {

    
    boolean saves(MtMessageText entity);
}

