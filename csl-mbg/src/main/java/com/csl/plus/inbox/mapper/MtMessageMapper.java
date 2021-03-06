package com.csl.plus.inbox.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csl.plus.inbox.entity.MtMessage;
import org.apache.ibatis.annotations.Mapper;

/**
 * 消息通知表
 * 
 * @author David
 * @email 
 * @date 2020-01-31 17:46:11
 */
@Mapper
public interface MtMessageMapper extends BaseMapper<MtMessage> {
	
}
