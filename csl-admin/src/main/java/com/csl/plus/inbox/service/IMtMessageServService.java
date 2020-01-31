package com.csl.plus.inbox.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.inbox.entity.MtMessageServ;


/**
 * 服务端站内信
 *
 * @author David
 * @email 
 * @date 2020-01-31 17:46:11
 */
public interface IMtMessageServService extends IService<MtMessageServ> {

    
    boolean saves(MtMessageServ entity);
}

