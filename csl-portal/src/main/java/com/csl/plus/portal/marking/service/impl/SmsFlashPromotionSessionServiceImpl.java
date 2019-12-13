package com.csl.plus.portal.marking.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.marking.entity.SmsFlashPromotionSession;
import com.csl.plus.marking.mapper.SmsFlashPromotionSessionMapper;
import com.csl.plus.portal.marking.service.ISmsFlashPromotionSessionService;

/**
 * <p>
 * 限时购场次表 服务实现类
 * </p>
 *
 * @since 2019-04-19
 */
@Service
public class SmsFlashPromotionSessionServiceImpl
		extends ServiceImpl<SmsFlashPromotionSessionMapper, SmsFlashPromotionSession>
		implements ISmsFlashPromotionSessionService {

}
