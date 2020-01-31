package com.csl.plus.inbox.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.inbox.entity.MtMessage;


/**
 * 消息通知表
 *
 * @author David
 * @email 
 * @date 2020-01-31 17:46:11
 */
public interface IMtMessageService extends IService<MtMessage> {

    
    boolean saves(MtMessage entity);
}

