package com.csl.plus.pms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csl.plus.oms.vo.CartProduct;
import com.csl.plus.pms.entity.PmsProduct;
import com.csl.plus.pms.vo.PmsProductResult;
import com.csl.plus.pms.vo.PromotionProduct;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商品信息 Mapper 接口
 * </p>
 *
 *  
 * @since 2019-04-19
 */
public interface PmsProductMapper extends BaseMapper<PmsProduct> {

    CartProduct getCartProduct(@Param("id") Long id);

    List<PromotionProduct> getPromotionProductList(@Param("ids") List<Long> ids);

    PmsProductResult getUpdateInfo(Long id);
}
