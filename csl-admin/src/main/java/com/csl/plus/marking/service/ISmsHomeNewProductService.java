package com.csl.plus.marking.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.marking.entity.SmsHomeNewProduct;

import java.util.List;

/**
 * <p>
 * 新鲜好物表 服务类
 * </p>
 *
 * @since 2019-04-19
 */
public interface ISmsHomeNewProductService extends IService<SmsHomeNewProduct> {
    /**
     * 修改推荐排序
     */
    int updateSort(Long id, Integer sort);
    /**
     * 更新推荐状态
     */
    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);
}
