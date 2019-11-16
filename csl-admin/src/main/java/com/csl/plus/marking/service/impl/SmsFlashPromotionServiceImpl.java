package com.csl.plus.marking.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.csl.plus.marking.entity.SmsFlashPromotion;
import com.csl.plus.marking.mapper.SmsFlashPromotionMapper;
import com.csl.plus.marking.service.ISmsFlashPromotionService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 限时购表 服务实现类
 * </p>
 *
 * @since 2019-04-19
 */
@Service
public class SmsFlashPromotionServiceImpl extends ServiceImpl<SmsFlashPromotionMapper, SmsFlashPromotion> implements ISmsFlashPromotionService {
    @Override
    public int updateStatus(Long id, Integer status) {
        SmsFlashPromotion flashPromotion = new SmsFlashPromotion();
        flashPromotion.setId(id);
        flashPromotion.setStatus(status);
         this.updateById(flashPromotion);
        return 1;
    }
}
