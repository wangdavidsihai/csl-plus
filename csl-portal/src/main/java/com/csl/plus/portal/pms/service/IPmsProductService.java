package com.csl.plus.portal.pms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.csl.plus.pms.entity.PmsProduct;
import com.csl.plus.pms.vo.PmsProductAndGroup;
import com.csl.plus.pms.vo.PmsProductResult;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @since 2019-04-19
 */
public interface IPmsProductService extends IService<PmsProduct> {

	PmsProductAndGroup getProductAndGroup(Long id);

	PmsProductResult getUpdateInfo(Long id);
}
