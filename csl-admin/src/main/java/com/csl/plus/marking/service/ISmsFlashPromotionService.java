package com.csl.plus.marking.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.marking.entity.SmsFlashPromotion;

/**
 * <p>
 * 限时购表 服务类
 * </p>
 *
 * @since 2019-04-19
 */
public interface ISmsFlashPromotionService extends IService<SmsFlashPromotion> {
    /**
     * 修改上下线状态
     */
    int updateStatus(Long id, Integer status);
}
