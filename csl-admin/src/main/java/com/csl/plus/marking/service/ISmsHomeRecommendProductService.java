package com.csl.plus.marking.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.marking.entity.SmsHomeRecommendProduct;

import java.util.List;

/**
 * <p>
 * 人气推荐商品表 服务类
 * </p>
 *
 * @since 2019-04-19
 */
public interface ISmsHomeRecommendProductService extends IService<SmsHomeRecommendProduct> {
    /**
     * 更新推荐状态
     */
    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);
    /**
     * 修改推荐排序
     */
    int updateSort(Long id, Integer sort);
}
